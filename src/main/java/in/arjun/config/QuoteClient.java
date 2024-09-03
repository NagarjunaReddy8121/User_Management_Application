package in.arjun.config;

import in.arjun.model.response.QuoteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://dummyjson.com/quotes/",name = "QUOTE-CLIENT")
public interface QuoteClient {

      @GetMapping("/random")
      public QuoteResponse getQuote();
}
