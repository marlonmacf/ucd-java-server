package domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * An entity User composed by six fields (id, name, email, password, inspector, score).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 *
 * @author mandrel
 */
@Entity
@Table(name = "user", schema = "ucd")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Byte score;

    public User() {
        // Default constructor.
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "{id:" + id + ", name:'" + name + ", email:'" + email + ", password:'" + password + ", score:" + score + "}";
    }

} // class User
