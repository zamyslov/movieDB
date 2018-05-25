package moviedb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "movie", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "movie_unique_name_idx")})
public class Movie extends AbstractBaseEntity {

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "year")
    @NotNull
    @Range(min = 1900)
    private int year;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "actors_movies",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    private Set<Actor> cast;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "favoriteMovies")
    private Set<User> users;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
    @JsonManagedReference(value = "movie")
    private Set<Vote> votes;

    public Movie() {
    }

    public Movie(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Movie(Integer id, String name, int year) {
        super(id);
        this.name = name;
        this.year = year;
    }

    public Movie(Integer id, String name, int year, Set<Actor> cast) {
        this(id, name, year);
        this.cast = cast;
    }

    public Movie(String name, int year, Set<Actor> cast, Set<Vote> votes) {
        this(name, year);
        this.cast = cast;
        this.votes = votes;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Actor> getCast() {
        return cast;
    }

    public void setCast(Set<Actor> cast) {
        this.cast = cast;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year &&
                Objects.equals(name, movie.name) &&
                Objects.equals(cast, movie.cast) &&
                Objects.equals(votes, movie.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
