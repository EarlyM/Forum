package ua.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.forum.dao.MessageDAO;
import ua.forum.dao.TopicDAO;
import ua.forum.dto.MessageDTO;
import ua.forum.model.Message;
import ua.forum.model.Topic;
import ua.forum.service.MessageService;

import java.util.Date;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private TopicDAO topicDAO;

    @Override
    public MessageDTO findById(Integer id) {
        Message message = messageDAO.find(id);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setParent(message.getParentId());
        messageDTO.setTopicId(message.getTopicId());
        messageDTO.setText(message.getMessage());
        messageDTO.setMessageId(message.getMessageId());
        messageDTO.setUsername(message.getUsername());

        return messageDTO;
    }

    @Override
    public void create(MessageDTO messageDTO) {
        boolean parent = messageDTO == null;

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Date date = new Date();
        Message message = new Message();
        message.setTopicId(messageDTO.getTopicId() == null ? 0 : messageDTO.getTopicId());
        message.setMessage(messageDTO.getText());
        message.setUsername(username);
        message.setParentId(messageDTO.getParent());
        message.setCreateData(date);

        Integer id = messageDAO.create(message);

        if(parent){
            messageDAO.find(id).setParentId(id);
        }

        Topic topic = topicDAO.find(messageDTO.getTopicId());
        topic.setUpdateDate(date);
    }

    @Override
    public void update(MessageDTO messageDTO) {
        Message message = messageDAO.find(messageDTO.getMessageId());
        message.setMessage(messageDTO.getText());
    }

    @Override
    public void delete(Integer id) {
        messageDAO.delete(id);
    }
}
