package shorten.url.service;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.ShortenBitlinkBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shorten.url.model.Url;
import shorten.url.repository.UrlRepository;

import java.util.*;

@Service
public class ShortenUrl {


    @Autowired
    private Bitly bitly;

    @Autowired
    private UrlRepository urlRepository;


    public void shorten(Url url) throws ApiException {

        ShortenBitlinkBody shortenBitlinkBody = null;

        String longUrl = url.getLongUrl();
        shortenBitlinkBody = bitly.shortUrl(longUrl);

        url.setShortUrl(shortenBitlinkBody.getLink());
        url.setBitlinkId(shortenBitlinkBody.getId());


        Optional<Url> urlAlreadyShortened = urlRepository.findByLongUrl(url.getLongUrl());

        if(urlAlreadyShortened.isPresent()) {
            urlAlreadyShortened.get().setShortUrl(shortenBitlinkBody.getLink());
            urlAlreadyShortened.get().setBitlinkId(shortenBitlinkBody.getId());
            urlAlreadyShortened.get().setShortened(urlAlreadyShortened.get().getShortened()+1);

            urlRepository.save(urlAlreadyShortened.get());
        }else{
            url.setShortened(1);
            urlRepository.save(url);
        }

    }

    /**
     * Return all Url with click statistics
     * @return
     * @throws ApiException
     */
    public List<Url> getStatistics() throws ApiException {

        List<Url> urls = urlRepository.findAll();

        for (Url url: urls){
            url.setClicks(bitly.getTotalClicksByBitlink(url.getBitlinkId()));
        }

        return urls;

    }


}
