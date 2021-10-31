package comarnavsaraf.bookstagramapp.book.repository;

import comarnavsaraf.bookstagramapp.book.model.Book;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface BookRepository extends CassandraRepository<Book, String> {
}
