package domain.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.entities.Complaint;
import domain.entities.User;
import domain.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String insert(@RequestParam(value = "description", defaultValue = "") String latitude, String longitude, String description, Integer userId) {
        try {
            Complaint complaint = new Complaint();
            complaint.setUser(new User(userId));
            complaint.setStatus("STARTED");
            complaint.setLatitude(latitude);
            complaint.setLongitude(longitude);
            complaint.setDescription(description);
            complaintRepository.save(complaint);
            return mapper.writeValueAsString(complaint);
        } catch (Exception ex) {
            return "Error creating the complaint: " + ex.toString();
        }
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("complaint") Integer complaintId, String description, Integer inspectorId, String status) {

        try {
            Complaint complaint = complaintRepository.findOne(complaintId);
            if (description != null) {
                complaint.setDescription(description);
            }
            if (inspectorId != null) {
                complaint.setInspector(new User(inspectorId));
                complaint.setStatus(status);
            }
            complaintRepository.save(complaint);
            return mapper.writeValueAsString(complaint);
        } catch (Exception ex) {
            return "Error updating the complaint: " + ex.toString();
        }
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("complaint") Integer complaintId) {
        try {
            complaintRepository.delete(new Complaint(complaintId));
            return "Complaint " + complaintId + " succesfully deleted!";
        } catch (Exception ex) {
            return "Error deleting the complaint:" + ex.toString();
        }
    }
}
