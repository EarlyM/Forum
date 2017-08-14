package ua.forum.service;

import ua.forum.dto.TopicDTO;
import ua.forum.dto.TopicDetailDTO;
import ua.forum.model.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> findAllTopic();
    TopicDTO findByIdForUpdate(Integer id);
    TopicDetailDTO findById(Integer id);
    Integer update(TopicDTO topicDTO);
    void delete(Integer id);
    Integer create(TopicDTO topicDTO);
}
