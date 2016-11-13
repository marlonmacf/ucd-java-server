package domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "complaint_photo", schema = "ucd")
public class ComplaintPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_complaint")
    @JsonIgnore
    private Complaint complaint;

    @NotNull
    private String extension;

    @NotNull
    private String name;

    @NotNull
    private String path;

    @NotNull
    private String base;

    public ComplaintPhoto() {
        // Default constructor.
    }

    public ComplaintPhoto(Integer id) {
        this.id = id;
    }

    public ComplaintPhoto(Integer id, Complaint complaint, String extension, String name, String path) {
        this.id = id;
        this.complaint = complaint;
        this.extension = extension;
        this.name = name;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "{id:" + id + ", idComplaint:" + complaint.getId() + ", extension:'" + extension + ", name:'" + name + ", pathPhoto:'" + path + ", basePhoto:'" + base + "}";
    }

}
