package org.wilson.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.wilson.account.Account;
import org.wilson.account.AccountRepository;
import org.wilson.message.UserMessage;
import org.wilson.message.UserMessageService;
import org.wilson.queue.QueueForm;

import javax.servlet.http.HttpSession;
import java.security.Principal;
/**
 * Created by wilsona on 6/11/16.
 */
@Aspect
@Component
public class QueueAspect {

    @Autowired
    private UserMessageService umService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HttpSession session;

    Logger log = LoggerFactory.getLogger(QueueAspect.class);

    @Before("execution(* org.wilson.queue.QueueController.queue(..))")
    public void persistQueueWrite(JoinPoint jp) throws Throwable{
        System.out.println("aspect");

        if (jp.getArgs().length < 3) return;

        System.out.println(jp.getSignature().getName());

        QueueForm queueForm = (QueueForm)jp.getArgs()[0];
        Principal principal = (Principal)jp.getArgs()[3];

        Account account;

        if(session.getAttribute("account") == null){
            System.out.println("Making new account");
            account = accountRepository.findOneByEmail(principal.getName());
        } else {
            System.out.println("Getting from session");
            account = (Account)session.getAttribute("account");
        }

        umService.save(new UserMessage(account.getId(), queueForm.getMessage()));

        String logMessage = "Wrote message " + queueForm.getMessage() + " to queue " + principal.getName();
        System.out.println("All messages:");
        for(UserMessage userMessage : umService.getRepository().findByUserId(account.getId())){
            System.out.println("\t" + userMessage.getMessage());
        }
        System.out.println(logMessage);
        log.debug(logMessage);
    }

}
