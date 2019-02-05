package net.alexdixon.noxidhraio.models.forms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Set;


@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 2, message = "First Name is invalid")
    private String first_name;


    @NotNull
    @Size(min = 2, message = "Last Name is invalid")
    private String last_name;

    @NotNull
    @Size(min = 5, message = "Email is invalid")
    private String email;

    @NotNull
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{4,11}", message = "Username must be between 5 and 12 characters, start with a letter, and contain only letters and numbers")
    private String username;


    @NotNull
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{4,11}", message = "Password must be between 5 and 12 characters, start with a letter, and contain only letters and numbers")
    private String password;

    private String job_title;

    @ManyToMany
    @JoinColumn(name = "id")
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public int getId() {
        return id;
    }

    public User() { }

    public User(String first_name, String last_name, String email, String username, String password, String job_title) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.job_title = job_title;
    }
}
