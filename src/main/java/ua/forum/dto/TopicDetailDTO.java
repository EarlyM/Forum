package ua.forum.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TopicDetailDTO implements Serializable {
    private Integer topicId;
    private Date createdDate;
    private String topicSubject;
    private String text;
    private String username;
    private Date updateDate;
    private List<MessageDetailDTO> messageList;

    public TopicDetailDTO() {
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getTopicSubject() {
        return topicSubject;
    }

    public void setTopicSubject(String topicSubject) {
        this.topicSubject = topicSubject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<MessageDetailDTO> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageDetailDTO> messageList) {
        this.messageList = messageList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicDetailDTO that = (TopicDetailDTO) o;

        if (!topicId.equals(that.topicId)) return false;
        if (!createdDate.equals(that.createdDate)) return false;
        if (!topicSubject.equals(that.topicSubject)) return false;
        if (!text.equals(that.text)) return false;
        if (!username.equals(that.username)) return false;
        if (!updateDate.equals(that.updateDate)) return false;
        return messageList.equals(that.messageList);

    }

    @Override
    public int hashCode() {
        int result = topicId.hashCode();
        result = 31 * result + createdDate.hashCode();
        result = 31 * result + topicSubject.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + updateDate.hashCode();
        result = 31 * result + messageList.hashCode();
        return result;
    }
}
