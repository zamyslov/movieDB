package moviedb.repository.datajpa;

import moviedb.model.Actor;
import moviedb.model.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMovieRepository extends JpaRepository<Movie, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Movie u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Movie save(Movie movie);

    Optional<Movie> getById(Integer id);

    @Override
    List<Movie> findAll(Sort sort);

}
