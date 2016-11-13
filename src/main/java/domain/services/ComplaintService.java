package domain.services;

import domain.entities.Complaint;
import domain.entities.ComplaintPhoto;
import domain.entities.User;
import domain.repositories.ComplaintPhotoRepository;
import domain.repositories.ComplaintRepository;
import domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ComplaintPhotoRepository complaintPhotoRepository;

    @Autowired
    private UserRepository userRepository;

    public Iterable<Complaint> fetchAll() {
        try {
            return complaintRepository.findAll();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Complaint fetchOne(Integer complaintId) {
        try {
            return complaintRepository.findOne(complaintId);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Complaint insert(String latitude, String longitude, String description, Integer idUser, ArrayList<String> photosBase) {
        try {
            Complaint complaint = new Complaint();
            complaint.setUser(new User(idUser));
            complaint.setStatus("STARTED");
            complaint.setLatitude(latitude);
            complaint.setLongitude(longitude);
            complaint.setDescription(description);
            complaint = complaintRepository.save(complaint);

            Set<ComplaintPhoto> complaintPhotos = new HashSet<>();
            for (String photoBase : photosBase) {
                ComplaintPhoto complaintPhoto = new ComplaintPhoto();
                complaintPhoto.setComplaint(complaint);
                complaintPhoto.setExtension(".jpg");
                complaintPhoto.setName("" + photosBase.indexOf(photoBase));
                complaintPhoto.setPath("/storage/complaint/" + complaint.getId().toString() + "/" + photosBase.indexOf(photoBase));
                complaintPhoto.setBase(photoBase);
                complaintPhotos.add(complaintPhotoRepository.save(complaintPhoto));
            }

            complaint.setComplaintPhotos(complaintPhotos);
            return complaint;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Complaint update(Integer idComplaint, String description) {

        try {
            Complaint complaint = complaintRepository.findOne(idComplaint);
            complaint.setDescription(description);
            return complaintRepository.save(complaint);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Boolean delete(Integer idComplaint) {
        try {
            complaintRepository.delete(new Complaint(idComplaint));
            return true;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Complaint inspect(Integer idComplaint, Integer idInspector) {
        try {
            Complaint complaint = complaintRepository.findOne(idComplaint);
            User inspector = userRepository.findOne(idInspector);

            //Update the complaint.
            complaint.setStatus("INSPECTED");
            complaint.setInspector(inspector);
            return complaintRepository.save(complaint);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Complaint check(Integer idComplaint) {
        try {
            Complaint complaint = complaintRepository.findOne(idComplaint);
            User inspector = userRepository.findOne(complaint.getInspector().getId());
            User user = userRepository.findOne(complaint.getUser().getId());

            //Add points to inspector.
            byte score = inspector.getScore();
            score += (byte) 1;
            inspector.setScore(score);
            userRepository.save(inspector);

            //Add points to user.
            score = user.getScore();
            score += (byte) 1;
            user.setScore(score);
            userRepository.save(user);

            //Update the complaint.
            complaint.setStatus("CHECKED");
            return complaintRepository.save(complaint);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Complaint denounce(Integer idComplaint) {
        try {
            Complaint complaint = complaintRepository.findOne(idComplaint);
            complaint.setStatus("DENOUNCED");
            return complaintRepository.save(complaint);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
