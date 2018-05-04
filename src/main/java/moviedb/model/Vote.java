package moviedb.model;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Vote {
    private User user;
    private Movie movie;
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
