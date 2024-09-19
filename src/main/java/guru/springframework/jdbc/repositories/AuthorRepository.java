package guru.springframework.jdbc.repositories;


import guru.springframework.jdbc.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);

    Page<Author> findAuthorByLastName(String lastName, Pageable pageable);

    @Query(value = "SELECT * FROM author a WHERE a.last_name = :lastName",
            countQuery = "SELECT COUNT(*) FROM author a WHERE a.last_name = :lastName",
            nativeQuery = true)
    Page<Author> findAllAuthorsByLastNameNativeQueryPageable(@Param("lastName") String lastName, Pageable pageable);
}
