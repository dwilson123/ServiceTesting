package org.wilson.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wilsona on 6/13/16.
 */
@RestController
public class UserMessageController {


    private UserMessageRepository repository;

    @Autowired
    public UserMessageController(UserMessageRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(value = "message/{id}", method = RequestMethod.GET, produces = {"application/xml", "application/json"})
    public UserMessage message(@PathVariable long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "messages/{id}", method = RequestMethod.GET, produces = {"application/xml", "application/json"})
    public List<UserMessage> messages(@PathVariable long id) {
        return repository.findByUserId(id);
    }
}
