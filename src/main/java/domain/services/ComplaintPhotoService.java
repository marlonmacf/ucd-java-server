package domain.services;

import domain.entities.Complaint;
import domain.entities.ComplaintPhoto;
import domain.repositories.ComplaintPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintPhotoService {

    @Autowired
    private ComplaintPhotoRepository complaintPhotoRepository;

    public Iterable<ComplaintPhoto> fetchAll() {
        try {
            return complaintPhotoRepository.findAll();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public ComplaintPhoto fetchOne(Integer complaintPhotoId) {
        try {
            return complaintPhotoRepository.findOne(complaintPhotoId);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public ComplaintPhoto insert(Integer idComplaint, String extension, String name, String path) {
        try {
            ComplaintPhoto complaintPhoto = new ComplaintPhoto();
            complaintPhoto.setComplaint(new Complaint(idComplaint));
            complaintPhoto.setExtension(extension);
            complaintPhoto.setName(name);
            complaintPhoto.setPath(path);
            return complaintPhotoRepository.save(complaintPhoto);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public ComplaintPhoto update(Integer idComplaintPhoto, Integer idComplaint, String extension, String name, String path) {

        try {
            ComplaintPhoto complaintPhoto = complaintPhotoRepository.findOne(idComplaintPhoto);
            complaintPhoto.setComplaint(new Complaint(idComplaint));
            complaintPhoto.setExtension(extension);
            complaintPhoto.setName(name);
            complaintPhoto.setPath(path);
            return complaintPhotoRepository.save(complaintPhoto);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Boolean delete(Integer idComplaintPhoto) {
        try {
            complaintPhotoRepository.delete(new ComplaintPhoto(idComplaintPhoto));
            return true;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
