package comarnavsaraf.bookstagramapp.userbooks.controller;

import comarnavsaraf.bookstagramapp.userbooks.model.UserBooks;
import comarnavsaraf.bookstagramapp.userbooks.model.UserBooksPrimaryKey;
import comarnavsaraf.bookstagramapp.userbooks.repository.UserBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class UserBooksController {

    @Autowired
    UserBooksRepository userBooksRepository;

    @PostMapping("/addUserBook")
    public ModelAndView addBookForUser(@RequestBody MultiValueMap<String, String> formData, @AuthenticationPrincipal OAuth2User principal){

        if (principal==null || principal.getAttribute("login")==null){
            return null;
        }
        UserBooks userBooks = new UserBooks();
        UserBooksPrimaryKey userBooksPrimaryKey = new UserBooksPrimaryKey();
        userBooksPrimaryKey.setUserId(principal.getAttribute("login"));
        String bookId = formData.getFirst("bookId");
        userBooksPrimaryKey.setBookId(bookId);
        userBooks.setStartedDate(LocalDate.parse(formData.getFirst("startDate")));
        userBooks.setCompletedDate(LocalDate.parse(formData.getFirst("completedDate")));
        userBooks.setRating(Integer.parseInt(formData.getFirst("rating")));
        userBooks.setStatus(formData.getFirst("readingStatus"));
        userBooks.setKey(userBooksPrimaryKey);
        userBooksRepository.save(userBooks);
        return new ModelAndView("redirect:/books/"+bookId);
    }
}
