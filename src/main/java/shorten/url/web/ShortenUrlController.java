package shorten.url.web;


import org.openapitools.client.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shorten.url.model.Url;
import shorten.url.repository.UrlRepository;
import shorten.url.service.ShortenUrl;
import javax.validation.Valid;


@Controller
public class ShortenUrlController {


    @Autowired
    private ShortenUrl shortenUrl;

    @Autowired
    private UrlRepository urlRepository;


    @ModelAttribute(name = "url")
    public Url url() {
        return new Url();
    }



    @GetMapping("/order")
    public String order(@ModelAttribute Url url) {

        return "order";

    }

    @PostMapping("/shorten")
    public String submitShorten(@Valid Url url, Errors errors) {

        if (errors.hasErrors()) {
            return "create";
        }

        try {
            shortenUrl.shorten(url);
        } catch (ApiException e) {
            System.err.println("Exception when creating Bitlink");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();

            return "order";
        }
        return "redirect:/statistics";

    }

    @GetMapping("/statistics")
    public String statistics(Model model) {

        try {

            model.addAttribute("urls",shortenUrl.getStatistics());

        } catch (ApiException e) {
            System.err.println("Exception when retrieving statistics");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }

        return "statistics";

    }


}
