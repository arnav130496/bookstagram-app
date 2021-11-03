package comarnavsaraf.bookstagramapp.book.controller;

import comarnavsaraf.bookstagramapp.book.model.Book;
import comarnavsaraf.bookstagramapp.book.repository.BookRepository;
import comarnavsaraf.bookstagramapp.userbooks.model.UserBooks;
import comarnavsaraf.bookstagramapp.userbooks.model.UserBooksPrimaryKey;
import comarnavsaraf.bookstagramapp.userbooks.repository.UserBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Optional;

@Controller
public class BookController {

    private final String COVER_IMAGE_ROOT = "http://covers.openlibrary.org/b/id/";

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserBooksRepository userBooksRepository;

    @GetMapping(value = "/books/{bookId}")
    //Model is a property of springframework UI where you can pass entity data to your html templates
    public String getBook(@PathVariable String bookId, Model model, @AuthenticationPrincipal OAuth2User principal){
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            String coverImageUrl ="/images/no-image.png";
            if(book.getCoverIds()!=null && book.getCoverIds().size()>0){
                coverImageUrl = COVER_IMAGE_ROOT + book.getCoverIds().get(0) + "-L.jpg";
            }
            model.addAttribute("coverImage",coverImageUrl);
            model.addAttribute("book", book);
            //whatever is being returned here refers to the name of the html file


            if (principal!=null && principal.getAttribute("login")!=null){
                model.addAttribute("loginId",principal.getAttribute("login"));
                UserBooksPrimaryKey primaryKey = new UserBooksPrimaryKey();
                primaryKey.setBookId(bookId);
                primaryKey.setUserId(principal.getAttribute("login"));
                Optional<UserBooks> userBooks = userBooksRepository.findById(primaryKey);
                if(userBooks.isPresent()){
                    model.addAttribute("userBooks", userBooks.get());
                }
                else {
                    model.addAttribute("userBooks",new UserBooks());
                }
            }
            return "book";
        }
        //whatever is being returned here refers to the name of the html file
        return "book-not-found";
    }


}
