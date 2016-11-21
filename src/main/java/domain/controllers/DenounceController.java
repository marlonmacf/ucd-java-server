package domain.controllers;

import domain.entities.Complaint;
import domain.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DenounceController {

    private ComplaintService complaintService;

    @Autowired
    public DenounceController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @RequestMapping(value = "/complaint/{complaint}/denounce", method = RequestMethod.GET)
    public Complaint index(@PathVariable("complaint") Integer idComplaint) {
        return complaintService.denounce(idComplaint);
    }
}
