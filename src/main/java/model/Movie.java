package model;

import java.util.Objects;
import java.util.Set;

public class Movie {
    private String name;
    private int year;
    private Set<Actor> cast;
    private Set<Vote> votes;

    public Movie() {
    }

    public Movie(String name, int year, Set<Actor> cast, Set<Vote> votes) {
        this.name = name;
        this.year = year;
        this.cast = cast;
        this.votes = votes;
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

        return Objects.hash(name, year, cast, votes);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
