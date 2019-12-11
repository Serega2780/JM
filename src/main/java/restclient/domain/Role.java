package restclient.domain;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
@Table(name = "roles", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "ROLE_UK", columnNames = "role")})
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role() {
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Role(int id, String role) {
        setId(id);
        setRole(role);
    }

    @Override
    public String getAuthority() {
        return this.getRole();
    }
}
