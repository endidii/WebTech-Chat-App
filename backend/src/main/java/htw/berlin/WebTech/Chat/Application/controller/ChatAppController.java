package htw.berlin.WebTech.Chat.Application.controller;

import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.service.MessageService;
import htw.berlin.WebTech.Chat.Application.service.TextchannelService;
import htw.berlin.WebTech.Chat.Application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ChatAppController {

    private final MessageService messageService;
    private final UserService userService;
    private final TextchannelService textchannelService;

    @PostMapping("/messages")
    public Message createMessage(@RequestParam String username, @RequestParam String content){
        // Find the user by username or create a new one
        User user = userService.getUserByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            userService.createUser(user);
        }
        // Create and save the new message
        Message message = new Message();
        message.setSender(user);
        message.setContent(content);
        return messageService.createMessage(message);
    }

    @PostMapping("/users")
    public User createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        return userService.createUser(user);
    }

    @GetMapping("/messages")
    public List<Message> findAll(@RequestParam(required = false)String content){
        if(content != null){
            return messageService.searchByContent(content);
        }
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{username}")
    public List<Message> getMessagesByUsername(@PathVariable String username) {
        return messageService.getMessagesByUser_Username(username);
    }

    @GetMapping("/")
    public String index() {
        return "Willkommen bei der Chat App!";
    }

}
