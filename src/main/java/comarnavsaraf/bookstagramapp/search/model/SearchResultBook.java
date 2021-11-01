package comarnavsaraf.bookstagramapp.search.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchResultBook {

    private String key;
    private String title;
    private List<String> author_name;
    private String cover_i;
}
