package domain.services;

import domain.DevelopmentTest;
import domain.entities.Complaint;
import domain.entities.ComplaintPhoto;
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
public class ComplaintPhotoServiceTest extends DevelopmentTest {

    @Autowired
    private ComplaintPhotoService complaintPhotoService;

    @Autowired
    private ComplaintService complaintService;

    @Test
    public void fetchAll() throws Exception {
        List<ComplaintPhoto> complaintPhotos = Lists.newArrayList(complaintPhotoService.fetchAll());
        assertTrue("Any complaint photo found", (!complaintPhotos.isEmpty()));
    }

    @Test
    public void fetchOne() throws Exception {
        ComplaintPhoto complaintPhoto = complaintPhotoService.fetchOne(1);
        assertTrue("Complaint photo not found", (complaintPhoto != null));
    }

    @Test
    public void insert() throws Exception {
        Complaint complaint = complaintService.fetchOne(1);
        assertTrue("Complaint not found", (complaint != null));

        ComplaintPhoto complaintPhoto;
        String photoExtension = ".png";
        String photoName = "PHOTO_TEST";
        String photoPath = "http:8080/";

        complaintPhoto = complaintPhotoService.insert(complaint.getId(), photoExtension, photoName, photoPath);
        assertTrue("Wrong complaint value", (complaintPhoto.getComplaint().getId().equals(complaint.getId())));
        assertTrue("Wrong extension value", (complaintPhoto.getExtension().equals(photoExtension)));
        assertTrue("Wrong name value", (complaintPhoto.getName().equals(photoName)));
        assertTrue("Wrong path value", (complaintPhoto.getPath().equals(photoPath)));
    }

    @Test
    public void update() throws Exception {
        ComplaintPhoto complaintPhoto = complaintPhotoService.fetchOne(1);
        assertTrue("Complaint photo not found", (complaintPhoto != null));

        Complaint complaint = complaintService.fetchOne(complaintPhoto.getComplaint().getId());
        assertTrue("Complaint photo not found", (complaint != null));

        String photoExtension = ".jpg";
        String photoName = "NEW_NAME";
        String photoPath = "http:8090/";

        complaintPhoto.setExtension(photoExtension);
        complaintPhoto.setName(photoName);
        complaintPhoto.setPath(photoPath);

        complaintPhoto = complaintPhotoService.update(complaintPhoto.getId(), complaint.getId(),
                complaintPhoto.getExtension(), complaintPhoto.getName(), complaintPhoto.getPath());

        assertTrue("Wrong complaint value", (complaintPhoto.getComplaint().getId().equals(complaint.getId())));
        assertTrue("Wrong extension value", (complaintPhoto.getExtension().equals(photoExtension)));
        assertTrue("Wrong name value", (complaintPhoto.getName().equals(photoName)));
        assertTrue("Wrong path value", (complaintPhoto.getPath().equals(photoPath)));
    }

    @Test
    public void delete() throws Exception {
        ComplaintPhoto complaintPhoto = complaintPhotoService.fetchOne(1);
        assertTrue("Complaint photo not found", (complaintPhoto != null));

        complaintPhotoService.delete(complaintPhoto.getId());
        complaintPhoto = complaintPhotoService.fetchOne(1);
        assertTrue("Complaint not deleted", (complaintPhoto == null));
    }
}