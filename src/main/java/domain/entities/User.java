package domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @Null
    private Boolean inspector;

    @Null
    private Byte score;

    public User() {
        // Default constructor.
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String name, String email, String password, Boolean inspecetor, Byte score) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.inspector = inspecetor;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public Boolean getInspactor() {
        return inspector;
    }

    public void setInspector(Boolean value) {
        this.inspector = value;
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte value) {
        this.score = value;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", inspector=" + inspector +
                ", score=" + score +
                '}';
    }

} // class User
