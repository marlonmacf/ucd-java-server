package domain.controllers;

import domain.entities.ComplaintPhoto;
import domain.services.ComplaintPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplaintPhotoController {

    private ComplaintPhotoService complaintPhotoService;

    @Autowired
    public ComplaintPhotoController(ComplaintPhotoService complaintPhotoService) {
        this.complaintPhotoService = complaintPhotoService;
    }

    @RequestMapping(value = "/complaint-photo", method = RequestMethod.GET)
    public Iterable<ComplaintPhoto> index() {
        return complaintPhotoService.fetchAll();
    }

    @RequestMapping(value = "/complaint-photo/{complaintPhoto}", method = RequestMethod.GET)
    public ComplaintPhoto show(@PathVariable("complaintPhoto") Integer complaintPhotoId) {
        return complaintPhotoService.fetchOne(complaintPhotoId);
    }

    @RequestMapping(value = "/complaint-photo", method = RequestMethod.POST)
    public ComplaintPhoto store(Integer idComplaint, String extension, String name, String path) {
        return complaintPhotoService.insert(idComplaint, extension, name, path);
    }

    @RequestMapping(value = "/complaint-photo/{complaintPhoto}", method = RequestMethod.PUT)
    public ComplaintPhoto update(@PathVariable("complaintPhoto") Integer idComplaintPhoto, Integer idComplaint,
                                 String extension, String name, String path) {

        return complaintPhotoService.update(idComplaintPhoto, idComplaint, extension, name, path);
    }

    @RequestMapping(value = "/complaint-photo/{complaintPhoto}", method = RequestMethod.DELETE)
    public Boolean destroy(@PathVariable("complaintPhoto") Integer idComplaintPhoto) {
        return complaintPhotoService.delete(idComplaintPhoto);
    }
}