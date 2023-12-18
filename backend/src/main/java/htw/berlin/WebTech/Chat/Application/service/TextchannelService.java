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

    public Textchannel createTextchannel(String name, String description, String userId){
        Textchannel textchannel = new Textchannel();
        textchannel.setId(UUID.randomUUID().toString().substring(0,5));
        textchannel.setName(name.replace(" ", "-"));
        textchannel.setDescription(description);
        User user = userRepository.findUserById(userId);
        user.getTextchannels().add(textchannel);
        return textchannelRepository.save(textchannel);
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

    public List<Textchannel> getAllTextchannelsByUserId(String userId){
        return textchannelRepository.findTextchannelsByUsersId(userId);
    }

    public List<User> getUsersOfTextChannel(String textChannelId) {
        return textchannelRepository.findUsersByChannelId(textChannelId);
    }
    public Textchannel getTextchannelById(String textChannelId) {
        return textchannelRepository.findTextchannelById(textChannelId);
    }
    public Textchannel getTextchannelByName(String name) {
        return textchannelRepository.findTextchannelByName(name.replace(" ", "-"));
    }

    public void addUserToChannelByName(String channelName, String userId){
        User user = userRepository.findUserById(userId);
        Textchannel textchannel = textchannelRepository.findTextchannelByName(channelName.replace(" ", "-"));

        textchannel.getUsers().add(user);
        textchannelRepository.save(textchannel);
    }

}
