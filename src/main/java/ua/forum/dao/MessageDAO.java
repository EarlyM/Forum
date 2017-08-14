package ua.forum.dao;

import ua.forum.model.Message;

import java.util.List;

public interface MessageDAO extends GenericDAO<Message, Integer> {
    List<Message> findAllByTopicId(Integer id);
}
