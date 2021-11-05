package comarnavsaraf.bookstagramapp.user.repository;
import comarnavsaraf.bookstagramapp.user.model.BooksByUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BooksByUserRepository extends CassandraRepository<BooksByUser, String> {

    Slice<BooksByUser> findAllById(String id, Pageable pageable);

}