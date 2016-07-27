package org.wilson.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wilsona on 6/13/16.
 */
public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {

    List<UserMessage> findByUserId(long userId);

}
