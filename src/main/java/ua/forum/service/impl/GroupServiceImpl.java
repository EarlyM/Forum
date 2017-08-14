package ua.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.forum.dao.GroupDAO;
import ua.forum.model.Group;
import ua.forum.service.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public List<Group> getAllGroup() {
        return groupDAO.findAll();
    }
}
