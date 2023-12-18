package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
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
        if(user != null){
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
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
}
