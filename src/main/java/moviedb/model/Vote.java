package moviedb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = "user_id, movie_id", name = "votes_idx")})
public class Vote {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "votes")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "votes")
    private Movie movie;

    @Column(name = "mark")
    @NotBlank
    @Range(min = 0, max = 5)
    private int mark;

    public Vote() {
    }

    public Vote(User user, Movie movie, int mark) {
        this.user = user;
        this.movie = movie;
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return mark == vote.mark &&
                Objects.equals(user, vote.user) &&
                Objects.equals(movie, vote.movie);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, movie, mark);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user=" + user +
                ", movie=" + movie +
                ", mark=" + mark +
                '}';
    }
}
