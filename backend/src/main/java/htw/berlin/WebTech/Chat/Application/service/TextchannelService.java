package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.repository.MessageRepository;
import htw.berlin.WebTech.Chat.Application.repository.TextchannelRepository;
import htw.berlin.WebTech.Chat.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TextchannelService {
    private final TextchannelRepository textchannelRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public Textchannel createTextchannel(String name, String description, String userId){
        Textchannel findTextchannel = textchannelRepository.findTextchannelByName(name.replace(" ", "-"));
        if(findTextchannel != null) {
            return null;
        }
        Textchannel textchannel = new Textchannel();
        textchannel.setId(UUID.randomUUID().toString().substring(0,5));
        textchannel.setName(name.replace(" ", "-"));
        textchannel.setDescription(description);
        textchannel.setCreatorId(userId);
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
    public void removeUserFromTextchannel(String userId, String channelId) {
        User user = userRepository.findUserById(userId);
        Textchannel textchannel = textchannelRepository.findTextchannelById(channelId);
        user.getTextchannels().remove(textchannel);
        textchannel.getUsers().remove(user);
        userRepository.save(user);
        textchannelRepository.save(textchannel);
    }

    public void deleteAllMessagesFromTextchannels() {
        List<Textchannel> allTextchannels = textchannelRepository.findAll();
        for (Textchannel textchannel : allTextchannels) {
            textchannel.getMessages().clear(); // Clear the messages from the textchannel
            textchannelRepository.save(textchannel);
        }
    }
    public void deleteAllTextchannels() {
        textchannelRepository.deleteAll();
    }

    public void deleteTextchannelByName(String name) {
        Textchannel textchannel = textchannelRepository.findTextchannelByName(name.replace(" ", "-"));
        textchannel.getUsers().forEach(user -> {
            Iterator<Message> iterator = user.getMessages().iterator();
            while (iterator.hasNext()) {
                Message message = iterator.next();
                if (message.getTextchannel().getId().equals(textchannel.getId())) {
                    iterator.remove();
                }
            }
        });
        textchannel.getUsers().forEach(user -> user.getTextchannels().remove(textchannel));
        if(!textchannel.getMessages().isEmpty()){
            for (int i = textchannel.getMessages().size() - 1; i >= 0; i--) {
                String messageId = textchannel.getMessages().get(i).getId();
                textchannel.getMessages().remove(i);
                messageRepository.deleteById(messageId);
            }
        }
        textchannelRepository.delete(textchannel);
    }


}
