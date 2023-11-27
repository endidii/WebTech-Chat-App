package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.model.Directchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.repository.DirectchannelRepository;
import htw.berlin.WebTech.Chat.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DirectchannelService {
    private final DirectchannelRepository directchannelRepository;
    private final UserRepository userRepository;

    public Directchannel createDirectchannel(String userId1, String userId2){
        Directchannel directchannel = new Directchannel();
        User user1 = userRepository.findUserById(userId1);
        User user2 = userRepository.findUserById(userId2);
        directchannel.setId(UUID.randomUUID().toString().substring(0,5));
        directchannel.getUsers().add(user1);
        directchannel.getUsers().add(user2);
        return directchannelRepository.save(directchannel);
    }
}
