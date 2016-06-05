package domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "complaint", schema = "ucd")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_inspector")
    private User inspector;

    @NotNull
    private String status;

    @NotNull
    private String latitude;

    @NotNull
    private String longitude;

    private String description;

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL)
    private Set<ComplaintPhoto> complaintPhotos;

    public Complaint() {
        // Default constructor.
    }

    public Complaint(Integer id) {
        this.id = id;
    }

    public Complaint(Integer id, User user, User inspector, String status, String latitude, String longitude, String description, Set<ComplaintPhoto> complaintPhotos) {
        this.id = id;
        this.user = user;
        this.inspector = inspector;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.complaintPhotos = complaintPhotos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getInspector() {
        return inspector;
    }

    public void setInspector(User inspector) {
        this.inspector = inspector;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ComplaintPhoto> getComplaintPhotos() {
        return complaintPhotos;
    }

    public void setComplaintPhotos(Set<ComplaintPhoto> complaintPhotos) {
        this.complaintPhotos = complaintPhotos;
    }

    @Override
    public String toString() {
        return "{id:" + id + ", user:" + user.toString() + ", inspector:" + inspector.toString() + ", status:" + status + ", latitude:" + latitude + ", longitude:" + longitude + "}";
    }

}
