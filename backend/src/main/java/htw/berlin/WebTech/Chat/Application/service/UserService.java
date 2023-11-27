package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(String username, String email, String password){
        User user = new User();
        user.setId(UUID.randomUUID().toString().substring(0,5));
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
