package domain.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourcesStorageController {

    @RequestMapping(value = "/storage/complaint/{idComplaint}/{photoName}", method = RequestMethod.GET)
    public byte[] complaintPhoto(@PathVariable("idComplaint") String idComplaint, @PathVariable("photoName") String photoName) {
        return null;
    }
}