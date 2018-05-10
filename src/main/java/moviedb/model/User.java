package moviedb.model;

import javax.persistence.Entity;
import java.util.Objects;
import java.util.Set;

@Entity
public class User extends AbstractBaseEntity {
    private String login;
    private String password;
    private String name;
    private Set<Role> roles;
    private Set<Vote> votes;
    private Set<Vote> favoriteMovies;

    public User() {
    }

    public User(String login, String password, String name, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public Set<Vote> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(Set<Vote> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(votes, user.votes) &&
                Objects.equals(name, user.name) &&
                Objects.equals(favoriteMovies, user.favoriteMovies);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, password, roles, votes, favoriteMovies);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + login + '\'' +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
