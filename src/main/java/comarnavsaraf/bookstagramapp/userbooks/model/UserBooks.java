package comarnavsaraf.bookstagramapp.userbooks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.*;
import java.time.LocalDate;

@Table("book_by_user_and_bookid")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBooks {

    @PrimaryKey
    private UserBooksPrimaryKey key;
    @Column(value = "reading_status")
    @CassandraType(type= CassandraType.Name.TEXT)
    private String status;
    @Column(value = "started_date")
    @CassandraType(type=CassandraType.Name.DATE)
    private LocalDate startedDate;
    @Column(value = "completed_date")
    @CassandraType(type=CassandraType.Name.DATE)
    private LocalDate completedDate;
    @Column(value = "rating")
    @CassandraType(type = CassandraType.Name.INT)
    private int rating;

}
