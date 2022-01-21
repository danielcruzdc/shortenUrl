package shorten.url.service;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.BitlinksApi;
import org.openapitools.client.auth.HttpBearerAuth;
import org.openapitools.client.model.*;
import org.springframework.stereotype.Service;

@Service
public class Bitly {

    private static final String DOMAIN = "bit.ly";
    private static final String GROUP_GUID = "Bm1jcdkuvpJ";
    private static final String BEARER_TOKEN = "522b5b920648fd362a2b89fa3b1548e3ae769913";

    private ApiClient apiClient;
    private BitlinksApi bitlinksApi;


    public Bitly (){

        apiClient = new ApiClient();
        ((HttpBearerAuth) apiClient.getAuthentication("bearerAuth")).setBearerToken(BEARER_TOKEN);
        bitlinksApi = new BitlinksApi(apiClient);


    }

    public ShortenBitlinkBody shortUrl(String longUrl) throws ApiException {

        Shorten shorten = new Shorten();

        shorten.setDomain(DOMAIN);
        shorten.setGroupGuid(GROUP_GUID);
        shorten.setLongUrl(longUrl);

        ShortenBitlinkBody result = bitlinksApi.createBitlink(shorten);
        System.out.println(result);
        return result;

    }



    public Integer getTotalClicksByBitlink(String bitLink) throws ApiException {

        ClicksSummary clicks = bitlinksApi.getClicksSummaryForBitlink(bitLink, TimeUnit.DAY,-1,null);
        return clicks.getTotalClicks();

    }
}
