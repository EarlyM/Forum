package ua.forum.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.forum.dao.AccountDAO;
import ua.forum.model.User;

import java.util.List;

@Service
@Transactional
public class AccountDAOImpl extends GenericDAOImpl<User, String> implements AccountDAO {
    @Override
    public List<User> findAll() {
        return null;
    }
}
