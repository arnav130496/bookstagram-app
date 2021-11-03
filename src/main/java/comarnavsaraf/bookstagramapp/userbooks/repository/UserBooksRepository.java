package comarnavsaraf.bookstagramapp.userbooks.repository;

import comarnavsaraf.bookstagramapp.userbooks.model.UserBooks;
import comarnavsaraf.bookstagramapp.userbooks.model.UserBooksPrimaryKey;
import org.springframework.data.cassandra.repository.CassandraRepository;


public interface UserBooksRepository extends CassandraRepository<UserBooks, UserBooksPrimaryKey> {
}
