package ua.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.forum.dao.AccountDAO;
import ua.forum.dto.UserDTO;
import ua.forum.model.Group;
import ua.forum.model.User;
import ua.forum.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public User findUser(String name) {
        return accountDAO.find(name);
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = null;
        if(isExistsAccount(user.getUsername())) return user;

        user = new User();
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setGroup(new Group(1));

        return user;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setGroup(new Group(userDTO.getRoleId()));

        accountDAO.update(user);
    }


    private boolean isExistsAccount(String username){
        return accountDAO.find(username) == null;
    }
}
