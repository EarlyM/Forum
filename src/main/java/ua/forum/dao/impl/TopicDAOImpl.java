package ua.forum.dao.impl;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import ua.forum.dao.TopicDAO;
import ua.forum.model.Topic;

import java.util.List;

@Service(value = "topicDAO")
public class TopicDAOImpl extends GenericDAOImpl<Topic, Integer> implements TopicDAO {

    @Override
    public List<Topic> findAll() {
        return getSession().createCriteria(Topic.class).addOrder(Order.desc("updateDate")).list();
    }
}
