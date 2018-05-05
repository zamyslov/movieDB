package moviedb.repository.datajpa;

import moviedb.model.Actor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudActorRepository extends JpaRepository<Actor, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Actor u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Actor save(Actor actor);

    Optional<Actor> getById(Integer id);

    @Override
    List<Actor> findAll(Sort sort);

}
