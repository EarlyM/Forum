package ua.forum.service;

import ua.forum.dto.UserDTO;
import ua.forum.model.User;

public interface AccountService {
    User findUser(String name);
    User createUser(UserDTO user);
    void updateUser(UserDTO userDTO);
}
