package moviedb.model;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Actor extends AbstractBaseEntity{
    private String name;
    private String surname;
    private int dob;

    public Actor() {
    }

    public Actor(String name, String surname, int dob) {
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

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return dob == actor.dob &&
                Objects.equals(name, actor.name) &&
                Objects.equals(surname, actor.surname);
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
