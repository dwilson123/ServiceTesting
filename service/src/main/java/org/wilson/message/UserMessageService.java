package org.wilson.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by wilsona on 6/13/16.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserMessageService {

    @Autowired
    UserMessageRepository repository;

    @Transactional
    public void save(UserMessage message){
        repository.save(message);
    }

    public UserMessageRepository getRepository(){
        return repository;
    }

}
