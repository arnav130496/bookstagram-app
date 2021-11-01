package comarnavsaraf.bookstagramapp.search.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchResult {

    private int numFound;
    private List<SearchResultBook> docs;
}
