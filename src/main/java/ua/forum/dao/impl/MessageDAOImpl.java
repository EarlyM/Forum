package ua.forum.dao.impl;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.forum.dao.MessageDAO;
import ua.forum.model.Message;

import java.util.List;

@Repository
public class MessageDAOImpl extends GenericDAOImpl<Message, Integer> implements MessageDAO {
    @Override
    public List<Message> findAllByTopicId(Integer topicId) {
        Session session = getSession();
        return session.createCriteria(Message.class).
                add(Restrictions.eq("topicId", topicId)).
                addOrder(Order.asc("parentId")).
                addOrder(Order.asc("messageId")).list();
    }
}
