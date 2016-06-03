package domain.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.entities.Complaint;
import domain.entities.User;
import domain.repositories.ComplaintRepository;
import domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ComplaintController {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

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
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.POST)
    @ResponseBody
    public String insert(@RequestParam(value = "description", defaultValue = "") String latitude, String longitude, String description, Integer idUser) {
        try {
            Complaint complaint = new Complaint();
            complaint.setUser(new User(idUser));
            complaint.setStatus("STARTED");
            complaint.setLatitude(latitude);
            complaint.setLongitude(longitude);
            complaint.setDescription(description);
            return mapper.writeValueAsString(complaintRepository.save(complaint));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("complaint") Integer idComplaint, String description) {

        try {
            Complaint complaint = complaintRepository.findOne(idComplaint);
            complaint.setDescription(description);
            return mapper.writeValueAsString(complaintRepository.save(complaint));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean delete(@PathVariable("complaint") Integer idComplaint) {
        try {
            complaintRepository.delete(new Complaint(idComplaint));
            return true;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}/inspect", method = RequestMethod.GET)
    @ResponseBody
    public String inspect(@PathVariable("complaint") Integer idComplaint, Integer idInspector) {
        try {
            Complaint complaint = complaintRepository.findOne(idComplaint);
            User inspector = userRepository.findOne(idInspector);

            //Update the complaint.
            complaint.setStatus("INSPECTED");
            complaint.setInspector(inspector);
            return mapper.writeValueAsString(complaintRepository.save(complaint));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}/check", method = RequestMethod.GET)
    @ResponseBody
    public String check(@PathVariable("complaint") Integer idComplaint) {
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
            return mapper.writeValueAsString(complaintRepository.save(complaint));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}/denounce", method = RequestMethod.GET)
    @ResponseBody
    public String denounce(@PathVariable("complaint") Integer idComplaint) {
        try {
            Complaint complaint = complaintRepository.findOne(idComplaint);
            complaint.setStatus("DENOUNCED");
            return mapper.writeValueAsString(complaintRepository.save(complaint));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
