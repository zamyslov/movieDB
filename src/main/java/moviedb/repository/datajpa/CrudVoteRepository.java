package moviedb.repository.datajpa;

import moviedb.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.movie.id=:movie_id AND v.user.id=:user_id")
    int delete(@Param("movie_id") int movie_id, @Param("user_id") int user_id);

    @Override
    List<Vote> findAll();

    @Query("SELECT v FROM Vote v WHERE v.user.id=:user_id")
    List<Vote> getByUser(@Param("user_id") int user_id);

    @Override
    @Transactional
    Vote save(Vote vote);

}
