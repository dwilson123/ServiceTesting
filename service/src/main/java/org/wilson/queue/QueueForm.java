package org.wilson.queue;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;

/**
 * Created by wilsona on 4/11/16.
 */
@Controller
public class QueueForm {

    @NotBlank
    private String queueName;

    @NotBlank
    private String message;


    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
