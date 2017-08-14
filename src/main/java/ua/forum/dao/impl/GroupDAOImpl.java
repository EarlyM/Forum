package ua.forum.dao.impl;

import ua.forum.dao.GroupDAO;
import ua.forum.model.Group;

import java.util.List;

public class GroupDAOImpl extends GenericDAOImpl<Group, Integer> implements GroupDAO {
    @Override
    public List<Group> findAll() {
        return null;
    }
}
