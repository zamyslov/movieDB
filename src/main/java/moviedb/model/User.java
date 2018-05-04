package moviedb.model;

import javax.persistence.Entity;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {
    private String login;
    private String password;
    private Set<Role> roles;
    private Set<Vote> votes;
    private Set<Vote> favoriteMovies;

    public User() {
    }

    public User(String login, String password, Set<Role> roles) {
        this.login = login;
        this.password = password;
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
                Objects.equals(favoriteMovies, user.favoriteMovies);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, password, roles, votes, favoriteMovies);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
