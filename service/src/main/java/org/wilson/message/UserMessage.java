package org.wilson.message;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity
@Table(name = "message")
@XmlRootElement
public class UserMessage implements java.io.Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private long userId;

    @Column
    private String message;

    protected UserMessage() {
    }

    public UserMessage(long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    @XmlAttribute
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @XmlAttribute
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlAttribute
    public long getId() {
        return id;
    }
}
