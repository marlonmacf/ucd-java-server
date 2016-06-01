package domain.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.entities.Complaint;
import domain.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ComplaintController {

    @Autowired
    private ComplaintRepository complaintRepository;

    private ObjectMapper mapper;

    public ComplaintController() {
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.GET)
    @ResponseBody
    public String fetch() {
        try {
            return mapper.writeValueAsString(complaintRepository.findAll());
        } catch (Exception ex) {
            return "Error finding the complaints: " + ex.toString();
        }
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.POST)
    @ResponseBody
    public String insert() {
        try {
            Complaint complaint = new Complaint();
            //TODO: add parameters
            complaintRepository.save(complaint);
            return "Complaint succesfully created! (id = " + complaint.getId() + ")";
        } catch (Exception ex) {
            return "Error creating the complaint: " + ex.toString();
        }
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("complaint") Integer complaintId) {
        try {
            Complaint complaint = complaintRepository.findOne(complaintId);
            //TODO: add parameters
            complaintRepository.save(complaint);
        } catch (Exception ex) {
            return "Error updating the complaint: " + ex.toString();
        }
        return "Complaint succesfully updated!";
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("complaint") Integer complaintId) {
        try {
            Complaint complaint = new Complaint(complaintId);
            complaintRepository.delete(complaint);
            return "Complaint succesfully deleted!";
        } catch (Exception ex) {
            return "Error deleting the complaint:" + ex.toString();
        }
    }
}
