package domain.services;

import domain.entities.Complaint;
import domain.entities.User;
import domain.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ComplaintPhotoService complaintPhotoService;

    @Autowired
    private UserService userService;

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

    public Complaint insert(String latitude, String longitude, String description, Integer idUser, String photosBase) {
        try {
            Complaint complaint = new Complaint();
            complaint.setUser(userService.fetchOne(idUser));
            complaint.setStatus("STARTED");
            complaint.setLatitude(latitude);
            complaint.setLongitude(longitude);
            complaint.setDescription(description);
            complaint = complaintRepository.save(complaint);

            for (String photoBase : photosBase.split(",")) {
                if (!photoBase.isEmpty()) {
                    List<String> fullPathName = Arrays.asList(photoBase.split("/"));
                    String fullName = fullPathName.get(fullPathName.size() -1);
                    String path = Arrays.asList(photoBase.split(fullName)).get(0);
                    String name = Arrays.asList(fullName.split("\\.")).get(0);
                    String extension = "." + Arrays.asList(fullName.split("\\.")).get(1);

                    complaintPhotoService.insert(complaint.getId(), extension, name, path);
                }
            }

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
            User inspector = userService.fetchOne(idInspector);

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
            User inspector = userService.fetchOne(complaint.getInspector().getId());
            User user = userService.fetchOne(complaint.getUser().getId());

            //Add points to inspector.
            byte score = inspector.getScore();
            score += (byte) 1;
            inspector.setScore(score);
            userService.update(inspector.getId(), inspector.getEmail(), inspector.getName(), inspector.getPassword(), inspector.getInspector(), inspector.getScore());

            //Add points to user.
            score = user.getScore();
            score += (byte) 1;
            user.setScore(score);
            userService.update(user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getInspector(), user.getScore());

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
