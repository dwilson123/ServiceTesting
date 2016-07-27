package org.wilson.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by wilsona on 4/11/16.
 */
@Controller
public class QueueController {


    @RequestMapping(value = "queue")
    public String queue(Model model){
        model.addAttribute(new QueueForm());
        return "queue/queue";
    }

    @RequestMapping(value = "queue", method = RequestMethod.POST)
    public String queue(@Valid @ModelAttribute QueueForm form, Errors errors, RedirectAttributes ra, Principal principal){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String name = principal.getName();

            channel.exchangeDeclare("RabbitExchange", "topic");

            channel.basicPublish("RabbitExchange", name, null, form.getMessage().getBytes("UTF-8"));

            System.out.println(" [x] Sent '" + form.getMessage() + "' to " + "RabbitExchange" + " as " + name);

            channel.close();
            connection.close();
        }catch (Exception ex){

            ex.printStackTrace();
        }

        return "queue/queue";
    }
}
