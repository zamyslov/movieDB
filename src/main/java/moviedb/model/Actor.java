package moviedb.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "actors", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "surname"}, name = "actors_unique_name_surname_idx")})
public class Actor extends AbstractBaseEntity {

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "surname")
    @NotBlank
    private String surname;

    @Column(name = "dob")
    @NotNull
    private LocalDate dob;

    @ManyToMany(mappedBy = "cast")
    private Set<Movie> movies;

    public Actor() {
    }

    public Actor(String name, String surname, LocalDate dob) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
    }

    public Actor(Integer id, String name, String surname, LocalDate dob) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Actor actor = (Actor) o;
        return Objects.equals(name, actor.name) &&
                Objects.equals(surname, actor.surname) &&
                Objects.equals(dob, actor.dob);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, surname, dob);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                '}';
    }
}
