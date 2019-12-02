package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role = "user";

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getName().equals(user.getName()) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), password);
    }

    public User() {
    }

    public User(String name, String password, String role, String email, String country) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.country = country;
    }

    public User(int id, String name, String role, String email, String country) {

        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.country = country;
    }

    public User(int id, String name, String password, String role, String email, String country) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
