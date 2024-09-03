package in.arjun.controller;

import in.arjun.config.QuoteClient;
import in.arjun.model.response.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuoteController {

    @Autowired
    private QuoteClient quoteClient;

     @GetMapping("/dashboard")
     public String getQuote(Model model){
        QuoteResponse quoteResponse = quoteClient.getQuote();
        String quote = quoteResponse.getQuote();
        model.addAttribute("quote",quote);
        return "dashboard";
    }
}
