package ua.forum.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {
    private int messageId;
    private Date createData;
    private Integer parentId;
    private String message;
    private String username;
    private int topicId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int massageId) {
        this.messageId = massageId;
    }

    @Basic
    @Column(name = "CREATE_DATA")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateData() {
        return createData;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }

    @Basic
    @Column(name = "PARENT_ID")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "TOPIC_ID")
    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (messageId != message.messageId) return false;
        if (topicId != message.topicId) return false;
        if (createData != null ? !createData.equals(message.createData) : message.createData != null) return false;
        if (parentId != null ? !parentId.equals(message.parentId) : message.parentId != null) return false;
        if (this.message != null ? !this.message.equals(message.message) : message.message != null) return false;
        if (username != null ? !username.equals(message.username) : message.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (createData != null ? createData.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + topicId;
        return result;
    }
}
