package moviedb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "users_unique_login_idx")})
public class User extends AbstractBaseEntity {
    @Column(name = "login")
    @NotBlank
    private String login;

    @Column(name = "password")
    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference(value = "votes")
    private Set<Vote> votes;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_movies",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "movie_id") }
    )
    private Set<Movie> favoriteMovies;

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

    public Set<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(Set<Movie> favoriteMovies) {
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
