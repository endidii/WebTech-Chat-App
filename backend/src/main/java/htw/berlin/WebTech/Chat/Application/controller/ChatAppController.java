package htw.berlin.WebTech.Chat.Application.controller;

import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.model.Textchannel;
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
@CrossOrigin(origins = "*")
public class ChatAppController {

    private final MessageService messageService;
    private final UserService userService;
    private final TextchannelService textchannelService;

    //create new user
    @PostMapping("/users")
    public User createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password){
        return userService.createUser(username, email, password);
    }
    //get user by username
    @GetMapping("/users/{username}")
    public User getUserById(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }

    //create new channel
    @PostMapping("/channels")
    public Textchannel createTextchannel(@RequestParam String name, @RequestParam String description){
        return textchannelService.createTextchannel(name, description);
    }

    //add new user to channel
    @PostMapping("/channels/{channelId}/users")
    public void addUserToChannel(@PathVariable("channelId") String channelId, @RequestParam String userId){
        textchannelService.addUserToTextchannel(userId, channelId);
    }

    //add message to channel
    @PostMapping("/channels/{channelId}/users/{userId}/messages")
    public void addMessageToChannel(@PathVariable("channelId") String channelId,
                                    @PathVariable("userId") String userId,
                                    @RequestParam String content){
        Message message = messageService.createMessage(userId,channelId,content);
        textchannelService.addMessageToTextchannel(message, channelId);
    }

    //get messages by channelId and userId
    @GetMapping("/channels/{channelId}/users/{userId}/messages")
    public List<Message> getMessagesByChannelIdAndUserId(@PathVariable("channelId") String channelId,
                                                         @PathVariable("userId") String userId) {
        return messageService.getMessagesByTextchannelAndUser(channelId, userId);
    }

    //get all messages from a channel
    @GetMapping("/channels/{channelId}/messages")
    public List<Message> getMessagesByChannelId(@PathVariable("channelId") String channelId){
        return messageService.getMessagesByTextchannel(channelId);
    }
    //get all channels by UserId
    @GetMapping("/users/{userId}/channels")
    public List<Textchannel> getChannelsByUserId(@PathVariable("userId") String userId){
        return textchannelService.getAllTextchannelsByUserId(userId);
    }

}
