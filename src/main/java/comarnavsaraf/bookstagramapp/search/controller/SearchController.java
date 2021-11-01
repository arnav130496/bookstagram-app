package comarnavsaraf.bookstagramapp.search.controller;

import comarnavsaraf.bookstagramapp.search.model.SearchResult;
import comarnavsaraf.bookstagramapp.search.model.SearchResultBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class SearchController {

    private final String COVER_IMAGE_ROOT = "http://covers.openlibrary.org/b/id/";

    private final WebClient webClient;
        public SearchController(WebClient.Builder webCliBuilder ){
        this.webClient = webCliBuilder.exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16*1024*1024))
                .build()).baseUrl("https://openlibrary.org/search.json").build();
    }

    @GetMapping(value = "/search")
    public String getSearchResults(@RequestParam String query, Model model){
        Mono<SearchResult> mono = this.webClient.get()
                .uri("?q={query}",query)
                .retrieve().bodyToMono(SearchResult.class);
        SearchResult results = mono.block();
        List<SearchResultBook> books = results.getDocs()
                .stream()
                .limit(10)
                .map(bookResult -> {
                    bookResult.setKey(bookResult.getKey().replace("/works/",""));
                    String cover_id = bookResult.getCover_i();
                    if(StringUtils.hasText(cover_id)){
                        cover_id = COVER_IMAGE_ROOT + cover_id + "-M.jpg";
                    }
                    else{
                        cover_id = "/images/no-image.png";
                    }
                    bookResult.setCover_i(cover_id);
                    return bookResult;
                })
                .collect(Collectors.toList());
        model.addAttribute("searchResults", books);
        log.info(books.toString());
        return "search";
    }
}
