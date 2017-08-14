package ua.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.forum.dao.MessageDAO;
import ua.forum.dao.TopicDAO;
import ua.forum.dto.MessageDetailDTO;
import ua.forum.dto.TopicDTO;
import ua.forum.dto.TopicDetailDTO;
import ua.forum.model.Message;
import ua.forum.model.Topic;
import ua.forum.service.TopicService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDAO topicDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public List<Topic> findAllTopic() {
        return topicDAO.findAll();
    }

    @Override
    public TopicDTO findByIdForUpdate(Integer id){
        Topic topic = topicDAO.find(id);

        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setTopicId(topic.getTopicId());
        topicDTO.setMessage(topic.getText());
        topicDTO.setSubject(topic.getTopicSubject());

        return topicDTO;
    }

    @Override
    public TopicDetailDTO findById(Integer id) {
        TopicDetailDTO topicDetail = new TopicDetailDTO();
        Topic topic = topicDAO.find(id);
        topicDetail.setTopicId(topic.getTopicId());
        topicDetail.setText(topic.getText());
        topicDetail.setUsername(topic.getUsername());
        topicDetail.setCreatedDate(topic.getCreatedDate());
        topicDetail.setUpdateDate(topic.getUpdateDate());
        topicDetail.setTopicSubject(topic.getTopicSubject());
        topicDetail.setMessageList(getMessageDetailList(messageDAO.findAllByTopicId(id)));
        return topicDetail;
    }

    @Override
    public Integer update(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setTopicId(topicDTO.getTopicId());
        topic.setTopicSubject(topicDTO.getSubject());
        topicDAO.update(topic);
        return topic.getTopicId();
    }

    @Override
    public void delete(Integer id) {
        topicDAO.delete(id);
    }

    @Override
    public Integer create(TopicDTO topicDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Topic topic = new Topic();
        topic.setText(topicDTO.getMessage());
        topic.setTopicSubject(topicDTO.getSubject());
        topic.setUsername(username);
        Date date = new Date();
        topic.setCreatedDate(date);
        topic.setUpdateDate(date);

        return topicDAO.create(topic);
    }

    private List<MessageDetailDTO> getMessageDetailList(List<Message> messagesList){
        List<MessageDetailDTO> messageDetailDTOList = new ArrayList<>();
        MessageDetailDTO messageDetailDTO;
        for(Message message : messagesList){
            messageDetailDTO = new MessageDetailDTO();
            messageDetailDTO.setMassageId(message.getMessageId());
            messageDetailDTO.setParentId(message.getParentId());
            messageDetailDTO.setCreateData(message.getCreateData());
            messageDetailDTO.setMessage(message.getMessage());
            messageDetailDTO.setUser(message.getUsername());
            messageDetailDTOList.add(messageDetailDTO);
        }

        return messageDetailDTOList;
    }
}
