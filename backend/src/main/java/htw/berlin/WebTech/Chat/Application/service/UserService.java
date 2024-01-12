package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.exception.InvalidPasswordException;
import htw.berlin.WebTech.Chat.Application.exception.UserNotFoundException;
import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.repository.MessageRepository;
import htw.berlin.WebTech.Chat.Application.repository.TextchannelRepository;
import htw.berlin.WebTech.Chat.Application.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final TextchannelRepository textchannelRepository;
    private final TextchannelService textchannelService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(String username, String email, String password){
        User user_exists = userRepository.findUserByEmail(email);
        if(user_exists != null){
            return null;
        }
        User user = new User();
        user.setId(UUID.randomUUID().toString().substring(0,5));
        user.setUsername(username);
        user.setEmail(email);
        // Hashing the password
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getUserById(String userId){
        return userRepository.findUserById(userId);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User loginUser(String email, String password){
        User user = userRepository.findUserByEmail(email);
        if (user == null)
            throw new UserNotFoundException("User with email " + email + " does not exist.");
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new InvalidPasswordException("Invalid password for email " + email);
        return user;
    }
    public void deleteAllMessagesFromUsers() {
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            user.getMessages().clear(); // Clear the messages from the textchannel
            userRepository.save(user);
        }
    }
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Transactional
    public void deleteUserByEmail(String email){
        User foundUser = getUserByEmail(email);
        if (foundUser == null) {
            throw new UserNotFoundException("User with email " + email + " does not exist.");
        }
        for (Message message : foundUser.getMessages()) {
            textchannelRepository.findTextchannelsByUsersId(foundUser.getId()).forEach(textchannel -> {
                textchannel.getMessages().remove(message);
                textchannelRepository.save(textchannel);
            });
        }
        textchannelRepository.findTextchannelsByUsersId(foundUser.getId()).forEach(textchannel -> {
            textchannel.getUsers().remove(foundUser);
            textchannelRepository.save(textchannel);
        });
        userRepository.deleteUserByEmail(email);
    }

}
