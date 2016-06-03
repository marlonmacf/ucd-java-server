package domain.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    private ObjectMapper mapper;

    public ComplaintController() {
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        try {
            return mapper.writeValueAsString(complaintService.fetchAll());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}")
    @ResponseBody
    public String show(@PathVariable("complaint") Integer complaintId) {
        try {
            return mapper.writeValueAsString(complaintService.fetchOne(complaintId));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint", method = RequestMethod.POST)
    @ResponseBody
    public String store(@RequestParam(value = "description", defaultValue = "") String latitude, String longitude, String description, Integer idUser) {
        try {
            return mapper.writeValueAsString(complaintService.insert(latitude, longitude, description, idUser));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("complaint") Integer idComplaint, String description) {
        try {
            return mapper.writeValueAsString(complaintService.update(idComplaint, description));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}", method = RequestMethod.DELETE)
    @ResponseBody
    public String destroy(@PathVariable("complaint") Integer idComplaint) {
        try {
            return mapper.writeValueAsString(complaintService.delete(idComplaint));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}/inspect", method = RequestMethod.GET)
    @ResponseBody
    public String inspect(@PathVariable("complaint") Integer idComplaint, Integer idInspector) {
        try {
            return mapper.writeValueAsString(complaintService.inspect(idComplaint, idInspector));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}/check", method = RequestMethod.GET)
    @ResponseBody
    public String check(@PathVariable("complaint") Integer idComplaint) {
        try {
            return mapper.writeValueAsString(complaintService.check(idComplaint));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/complaint/{complaint}/denounce", method = RequestMethod.GET)
    @ResponseBody
    public String denounce(@PathVariable("complaint") Integer idComplaint) {
        try {
            return mapper.writeValueAsString(complaintService.denounce(idComplaint));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
