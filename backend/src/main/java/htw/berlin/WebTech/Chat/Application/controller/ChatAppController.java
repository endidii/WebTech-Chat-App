package htw.berlin.WebTech.Chat.Application.controller;

import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.service.MessageService;
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

    @PostMapping("/api/message")
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
        message.setUser(user);
        message.setContent(content);
        return messageService.createMessage(message);
    }

    @GetMapping("/api/message")
    public List<Message> findAll(@RequestParam(required = false)String content){
        if(content != null){
            return messageService.searchByContent(content);
        }
        return messageService.getAllMessages();
    }

    @GetMapping("/api/message/by-user/{username}")
    public List<Message> getMessagesByUsername(@PathVariable String username) {
        return messageService.getMessagesByUser_Username(username);
    }

    @GetMapping("/")
    public String index() {
        return "Willkommen bei der Chat App!";
    }

}
