package domain.controllers;

import domain.entities.Complaint;
import domain.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InspectController {

    @Autowired
    private ComplaintService complaintService;

    @RequestMapping(value = "/complaint/{complaint}/check", method = RequestMethod.GET)
    public Complaint check(@PathVariable("complaint") Integer idComplaint) {
        return complaintService.check(idComplaint);
    }
}
