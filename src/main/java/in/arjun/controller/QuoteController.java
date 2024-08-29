package in.arjun.controller;

import in.arjun.model.response.QuoteResponse;
import in.arjun.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/getQuote")
    public ResponseEntity<QuoteResponse> getRandomQuote(){
        QuoteResponse randomQuote = quoteService.getRandomQuote();
        return new ResponseEntity<>(randomQuote, HttpStatus.OK);
    }
}
