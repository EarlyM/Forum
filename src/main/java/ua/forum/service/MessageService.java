package ua.forum.service;

import ua.forum.dto.MessageDTO;

public interface MessageService {
    MessageDTO findById(Integer id);
    void create(MessageDTO messageDTO);
    void update(MessageDTO messageDTO);
    void delete(Integer id);
}
