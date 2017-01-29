package app.dbService.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 * Created by alexa on 22.01.2017.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    @Size(min=3,max=20,message = "Login must be between 3 and 20 characters long.")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$",message = "Login must be alphanumeric with no spaces")
    private String login;

    @Column(name = "password")
    @Size(min=6,max=35,message = "The password must be between 6 and 35 characters long.")
    @NotNull
    private String password;

    @Column(name = "email")
    @Email(message = "Invalid email address.")
    @NotNull
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(){
        this.id = -1;
    }
}
