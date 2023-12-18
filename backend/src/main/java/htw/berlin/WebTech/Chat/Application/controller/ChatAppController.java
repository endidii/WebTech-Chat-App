package htw.berlin.WebTech.Chat.Application.controller;

import htw.berlin.WebTech.Chat.Application.model.Directchannel;
import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.service.DirectchannelService;
import htw.berlin.WebTech.Chat.Application.service.MessageService;
import htw.berlin.WebTech.Chat.Application.service.TextchannelService;
import htw.berlin.WebTech.Chat.Application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatAppController {

    private final MessageService messageService;
    private final UserService userService;
    private final TextchannelService textchannelService;
    private final DirectchannelService directchannelService;

    //create new user
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User created_user = userService.createUser(user.getUsername(), user.getEmail(), user.getPassword());
        if (created_user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exists");
        }
        return ResponseEntity.ok(created_user);
    }

    //create new channel
    @PostMapping("/channels/users/{userId}")
    public ResponseEntity<?> createTextchannel(@RequestBody Textchannel textchannel, @PathVariable("userId") String userId){
        Textchannel created_textchannel = textchannelService.createTextchannel(textchannel.getName(), textchannel.getDescription(), userId);
        if (created_textchannel == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Channel already exists");
        }
        return ResponseEntity.ok(created_textchannel);
    }

    //create new direct channel
    @PostMapping("/directchannels")
    public Directchannel createDirectchannel(String userId1, String userId2){
        return directchannelService.createDirectchannel(userId1, userId2);
    }
    //get user by username
    @GetMapping("/users/username/{username}")
    public User getUserById(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }
    //get user by Email
    @GetMapping("/users/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {
        User foundUser = userService.getUserByEmail(email);
        if (foundUser == null) {
            // User not found, return 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // User found, return 200 OK with the user data
        return ResponseEntity.ok(foundUser);
    }

    //add new user to channel
    @PostMapping("/channels/{channelId}/users/{userId}")
    public void addUserToChannel(@PathVariable("channelId") String channelId, @PathVariable("userId") String userId){
        textchannelService.addUserToTextchannel(userId, channelId);
    }

    //add message to channel
    @PostMapping("/channels/{channelId}/users/{userId}/messages")
    public void addMessageToChannel(@RequestBody Message message_input,
                                    @PathVariable("channelId") String channelId,
                                    @PathVariable("userId") String userId){
        Message message = messageService.createMessage(userId,channelId,message_input.getContent());
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
    //get users by channelId
    @GetMapping("/channels/{channelId}/users")
    public List<User> getUsersOfTextChannel(@PathVariable("channelId") String channelId){
        return textchannelService.getUsersOfTextChannel(channelId);
    }
    //get user by userID
    @GetMapping("/users/{userId}")
    public User getUsersById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    //get channel by channelId
    @GetMapping("/channels/{channelId}")
    public Textchannel getTextchannelById(@PathVariable("channelId") String channelId){
        return textchannelService.getTextchannelById(channelId);
    }
    //get textchannel by name
    @GetMapping("/channels/name/{name}")
    public Textchannel getTextchannelByName(@PathVariable("name") String name){
        return textchannelService.getTextchannelByName(name);
    }

    @PostMapping("/channels/name/{channelName}/users/{userId}")
    public void addUserToChannelByName(@PathVariable("channelName") String channelName, @PathVariable("userId") String userId){
        textchannelService.addUserToChannelByName(channelName, userId);
    }

    @GetMapping("/")
    public String getHello(){
        return "Hello World";
    }

    @DeleteMapping("/channels/{channelId}/users/{userId}")
    public ResponseEntity<?> removeUserFromChannel(@PathVariable("channelId") String channelId, @PathVariable("userId") String userId) {
        textchannelService.removeUserFromTextchannel(userId, channelId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/textchannels/delete-all-messages")
    public ResponseEntity<?> deleteAllMessagesFromTextchannels() {
        textchannelService.deleteAllMessagesFromTextchannels();
        userService.deleteAllMessagesFromUsers();
        messageService.deleteAllMessages();
        userService.deleteAllUsers();
        textchannelService.deleteAllTextchannels();
        return ResponseEntity.ok("All messages from every textchannel and user have been deleted.");
    }

}
