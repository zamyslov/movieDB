package moviedb.repository.datajpa;

import moviedb.model.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
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

    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM Movie u WHERE u.id=?1")
    Movie getWithVotes(int id);

    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT avg(mark) FROM Vote v WHERE v.movie.id=?1")
    double getAverageMark(int id);
}
