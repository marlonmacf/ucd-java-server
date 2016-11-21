package domain.controllers;

import domain.entities.Complaint;
import domain.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComplaintController {

    private ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.GET)
    public Iterable<Complaint> index() {
        return complaintService.fetchAll();
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.GET)
    public Complaint show(@PathVariable("complaint") Integer complaintId) {
        return complaintService.fetchOne(complaintId);
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.POST)
    public Complaint store(String latitude, String longitude, String description, Integer idUser, String photosBase) {
        return complaintService.insert(latitude, longitude, description, idUser, photosBase);
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.PUT)
    public Complaint update(@PathVariable("complaint") Integer idComplaint, String description) {
        return complaintService.update(idComplaint, description);
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.DELETE)
    public Boolean destroy(@PathVariable("complaint") Integer idComplaint) {
        return complaintService.delete(idComplaint);
    }
}