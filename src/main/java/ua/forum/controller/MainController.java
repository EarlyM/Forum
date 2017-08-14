package ua.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.forum.dto.*;
import ua.forum.model.Topic;
import ua.forum.model.User;
import ua.forum.service.AccountService;
import ua.forum.service.MessageService;
import ua.forum.service.TopicService;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(ModelMap modelMap){
        List<Topic> topicList = topicService.findAllTopic();

        modelMap.put("topicList", topicList);
        return "index";
    }

    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    public String getTopic(@PathVariable("id") Integer id, ModelMap modelMap){
        TopicDetailDTO topic = topicService.findById(id);
        modelMap.addAttribute("topic", topic);
        modelMap.addAttribute("message", new MessageDTO());
        return "topic";
    }

    @RequestMapping(value = "/topic/edit/{id}", method = RequestMethod.GET)
    public String editTopic(@PathVariable("id") Integer id, ModelMap modelMap){
        modelMap.addAttribute("topic", topicService.findByIdForUpdate(id));
        return "createTopic";
    }

    @RequestMapping(value = "/topic/edit", method = RequestMethod.POST)
    public String editTopic(@ModelAttribute TopicDTO topicDTO){
        Integer id = topicService.update(topicDTO);
        return "redirect:/topic/" + id;
    }

    @RequestMapping(value = "/topic/add", method = RequestMethod.GET)
    public String createTopic(ModelMap modelMap){
        modelMap.addAttribute("topic", new TopicDTO());
        return "createTopic";
    }

    @RequestMapping(value = "/topic/add",method = RequestMethod.POST)
    public String createTopic(@ModelAttribute TopicDTO topic){
        Integer id = topicService.create(topic);
        return "redirect:   topic/" + id;
    }

    @RequestMapping(value = "/topic/{id}/add", method = RequestMethod.POST)
    public String createMessage(@ModelAttribute MessageDTO messageDTO, @PathVariable("id") Integer id){
        messageDTO.setTopicId(id);

        messageService.create(messageDTO);

        return "redirect:/topic/" + id;
    }

    @RequestMapping(value = "/topic/delete/{id}", method = RequestMethod.GET)
    public String deleteTopic(@PathVariable("id") Integer id){
        topicService.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/topic/{id}/delete/{messageId}", method = RequestMethod.GET)
    public String deleteMessage(@PathVariable("id") Integer id, @PathVariable("messageId") Integer messageId){
        messageService.delete(messageId);
        return "redirect:/topic/" + id;
    }

    @RequestMapping(value = "/topic/{id}/edit/{messageId}", method = RequestMethod.GET)
    public String editMessage(@PathVariable("id") Integer id, @PathVariable("messageId") Integer messageId, ModelMap modelMap){
        modelMap.addAttribute("message", messageService.findById(messageId));
        return "";
    }

    @RequestMapping(value = "/topic/{id}/edit", method = RequestMethod.POST)
    public String editMessage(@ModelAttribute MessageDTO messageDTO, @PathVariable("id") Integer id){
        messageService.update(messageDTO);
        return "redirect:/topic/" + id;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String userRegistration(ModelMap modelMap){
        modelMap.addAttribute("user", new UserDTO());
        return "";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String userRegistration(@ModelAttribute UserDTO userDTO, BindingResult bindingResult){
        User user = null;
        if(!bindingResult.hasErrors()){
            user = accountService.createUser(userDTO);
        }
        if(user == null){
            bindingResult.reject("Пользователь с таким именем уже существует");
        }

        return "redirect:/";
    }


}
