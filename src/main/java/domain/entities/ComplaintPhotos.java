package domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "complaintPhotos", schema = "ucd")
public class ComplaintPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idcomplaint")
    private Complaint complaint;

    @NotNull
    private String extension;

    @NotNull
    private String name;

    @NotNull
    private String pathphoto;

    public ComplaintPhotos() {
        // Default constructor.
    }

    public ComplaintPhotos(Integer id) {
        this.id = id;
    }

    public ComplaintPhotos(String extension, String name, String pathphoto) {
        this.extension = extension;
        this.name = name;
        this.pathphoto = pathphoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathphoto() {
        return pathphoto;
    }

    public void setPathphoto(String pathphoto) {
        this.pathphoto = pathphoto;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", idComplaint:" + complaint.getId() +
                ", extension:'" + extension +
                ", name:'" + name +
                ", pathPhoto:'" + pathphoto +
                '}';
    }

}
