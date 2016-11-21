package domain.services;

import domain.DevelopmentTest;
import domain.entities.Complaint;
import domain.entities.ComplaintPhoto;
import domain.entities.User;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Component
@RunWith(SpringRunner.class)
@Sql({"classpath:/database/data.sql"})
public class ComplaintServiceTest extends DevelopmentTest {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private UserService userService;

    @Autowired
    private ComplaintPhotoService complaintPhotoService;

    @Test
    public void fetchAll() throws Exception {
        List<Complaint> complaints = Lists.newArrayList(complaintService.fetchAll());

        assertTrue("Any complaint found", (!complaints.isEmpty()));

        for (Complaint complaint : complaints) {
            assertTrue("Complaints without photos", (complaint.getComplaintPhotos().size() > 0));
        }
    }

    @Test
    public void fetchOne() throws Exception {
        Complaint complaint = complaintService.fetchOne(1);

        assertTrue("Complaint not found", (complaint != null));
        assertTrue("Complaint without photos", (complaint.getComplaintPhotos().size() > 0));
    }

    @Test
    public void insert() throws Exception {
        String latitude = "-10.101010";
        String longitude = "-10.101010";
        String description = "DESCRIPTION_TEST";
        String photoPath = "http:8080/";
        String photoName = "PHOTO_TEST";
        String photoExtension = ".png";
        String photosBase = photoPath + photoName + photoExtension;

        User user = userService.fetchOne(1);
        Complaint complaint = complaintService.insert(latitude, longitude, description, user.getId(), photosBase);

        assertTrue("Complaint not found", complaint.getId() != null);
        assertTrue("Wrong inspector value", complaint.getInspector() == null);
        assertTrue("Wrong status value", complaint.getStatus().equals("STARTED"));
        assertTrue("Wrong latitude value", complaint.getLatitude().equals(latitude));
        assertTrue("Wrong longitude value", complaint.getLongitude().equals(longitude));
        assertTrue("Wrong description value", complaint.getDescription().equals(description));
        assertTrue("Wrong user value", complaint.getUser().getId().equals(user.getId()));

        for (ComplaintPhoto complaintPhoto : complaint.getComplaintPhotos()) {
            assertTrue("Photo with wrong user", complaintPhoto.getComplaint().getId().equals(complaint.getId()));
            assertTrue("Photo with wrong path", complaintPhoto.getPath().equals(photoPath));
            assertTrue("Photo with wrong name", complaintPhoto.getName().equals(photoName));
            assertTrue("Photo with wrong extension", complaintPhoto.getExtension().equals(photoExtension));
        }
    }

    @Test
    public void update() throws Exception {
        Complaint complaint = complaintService.fetchOne(1);
        assertTrue("Complaint not found", (complaint != null));

        String description = "NEW_DESCRIPTION_TEST";
        complaint = complaintService.update(complaint.getId(), description);
        assertTrue("Wrong description value", complaint.getDescription().equals(description));
    }

    @Test
    public void delete() throws Exception {
        Complaint complaint = complaintService.fetchOne(1);
        assertTrue("Complaint not found", (complaint != null));

        for (ComplaintPhoto complaintPhoto : complaint.getComplaintPhotos()) {
            complaintPhotoService.delete(complaintPhoto.getId());
        }

        complaint = complaintService.fetchOne(1);
        assertTrue("Complaint do not delete their photos", complaint.getComplaintPhotos().size() == 0);

        complaintService.delete(complaint.getId());
        complaint = complaintService.fetchOne(1);
        assertTrue("Complaint not deleted", (complaint == null));
    }

    @Test
    public void inspect() throws Exception {
        Complaint complaint = complaintService.fetchOne(1);
        User user = userService.fetchOne(1);
        User inspector = userService.fetchOne(2);
        assertTrue("Complaint not found", (complaint != null));
        assertTrue("User not found", (user != null));
        assertTrue("Inspector not found", (inspector != null));

        complaint = complaintService.inspect(complaint.getId(), user.getId());
        assertTrue("User must be a inspector", (complaint.getStatus().equals("STARTED")));

        complaint = complaintService.inspect(complaint.getId(), inspector.getId());
        assertTrue("Wrong complaint status", (complaint.getStatus().equals("INSPECTED")));
    }

    @Test
    public void check() throws Exception {
        Complaint complaint = complaintService.fetchOne(1);
        User inspector = userService.fetchOne(2);
        assertTrue("Complaint not found", (complaint != null));
        assertTrue("Inspector not found", (inspector != null));

        complaint = complaintService.inspect(complaint.getId(), inspector.getId());
        assertTrue("Wrong complaint status", (complaint.getStatus().equals("INSPECTED")));

        Byte userScore = complaint.getUser().getScore();
        Byte inspectorScore = complaint.getInspector().getScore();

        complaint = complaintService.check(complaint.getId());
        assertTrue("Wrong complaint status", (complaint.getStatus().equals("CHECKED")));
        assertTrue("User do not increased their score", (complaint.getUser().getScore() > userScore));
        assertTrue("Inspector do not increased their score", (complaint.getInspector().getScore() > inspectorScore));
    }

    @Test
    public void denounce() throws Exception {
        Complaint complaint = complaintService.fetchOne(1);
        assertTrue("Complaint not found", (complaint != null));

        Byte userScore = complaint.getUser().getScore();

        complaint = complaintService.denounce(complaint.getId());
        assertTrue("Wrong complaint status", (complaint.getStatus().equals("DENOUNCED")));
        assertTrue("User do not decreased their score", (complaint.getUser().getScore() < userScore));
    }
}