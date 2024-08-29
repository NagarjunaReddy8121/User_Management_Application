package in.arjun.service;

import in.arjun.model.response.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    @Autowired
    private RestTemplate restTemplate;

    public QuoteResponse getRandomQuote(){
        String apiUrl = "https://type.fit/api/quotes";
        QuoteResponse response = restTemplate.getForObject(apiUrl, QuoteResponse.class);
        return response;
    }
}
