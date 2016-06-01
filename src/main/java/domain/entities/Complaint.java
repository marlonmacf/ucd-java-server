package domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;

@Entity
@Table(name = "complaint", schema = "ucd")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;

    @Null
    @ManyToOne
    @JoinColumn(name = "idinspector")
    private User inspector;

    @NotNull
    private Boolean status;

    @NotNull
    private String latitude;

    @NotNull
    private String longitude;

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL)
    private Set<ComplaintPhotos> complaintPhotos;

    public Complaint() {
        // Default constructor.
    }

    public Complaint(Integer id) {
        this.id = id;
    }

    public Complaint(User user, Boolean status, String latitude, String longitude, Set<ComplaintPhotos> complaintPhotos) {
        this.user = user;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.complaintPhotos = complaintPhotos;
    }

    public Complaint(User user, User inspector, Boolean status, String latitude, String longitude, Set<ComplaintPhotos> complaintPhotos) {
        this.user = user;
        this.inspector = inspector;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public Set<ComplaintPhotos> getComplaintPhotos() {
        return complaintPhotos;
    }

    public void setComplaintPhotos(Set<ComplaintPhotos> complaintPhotos) {
        this.complaintPhotos = complaintPhotos;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", idUser:" + user.getId() +
                ", idInspector:" + inspector.getId() +
                ", status:" + status +
                ", latitude:'" + latitude +
                ", longitude:'" + longitude +
                '}';
        //TODO: add the set of photos..
    }

}
