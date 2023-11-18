package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.repository.MessageRepository;
import htw.berlin.WebTech.Chat.Application.repository.TextchannelRepository;
import htw.berlin.WebTech.Chat.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TextchannelService {
    private final TextchannelRepository textchannelRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public Textchannel createTextchannel(String name, String description){
        Textchannel textchannel = new Textchannel();
        textchannel.setId(UUID.randomUUID().toString().substring(0,5));
        textchannel.setName(name);
        textchannel.setDescription(description);
        return textchannelRepository.save(textchannel);
    }

    public List<Textchannel> getAllTextchannels() {
        return textchannelRepository.findAll();
    }

    public void addUserToTextchannel(String userId, String channelId){
        User user = userRepository.findUserById(userId);
        Textchannel textchannel = textchannelRepository.findTextchannelById(channelId);

        textchannel.getUsers().add(user);
        textchannelRepository.save(textchannel);
    }

    public void addMessageToTextchannel(Message message, String channelId){
        Textchannel textchannel = textchannelRepository.findTextchannelById(channelId);

        textchannel.getMessages().add(message);
        textchannelRepository.save(textchannel);
    }


}
