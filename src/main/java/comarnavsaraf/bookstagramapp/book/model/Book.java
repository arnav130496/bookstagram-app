package comarnavsaraf.bookstagramapp.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Table(value = "book_by_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @PrimaryKeyColumn(name="book_id",ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;
    @Column(value="book_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;
    @Column(value="book_description")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;
    @Column(value = "published_date")
    @CassandraType(type=CassandraType.Name.DATE)
    private LocalDate publishedDate;
    @Column(value = "cover_ids")
    @CassandraType(type=CassandraType.Name.LIST, typeArguments = CassandraType.Name.TEXT)
    private List<String> coverIds;
    @Column(value = "author_names")
    @CassandraType(type=CassandraType.Name.LIST, typeArguments = CassandraType.Name.TEXT)
    private List<String> authorNames;
    @Column(value = "author_ids")
    @CassandraType(type=CassandraType.Name.LIST, typeArguments = CassandraType.Name.TEXT)
    private List<String> authorIds;

}
