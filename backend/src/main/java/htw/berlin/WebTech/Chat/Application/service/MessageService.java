package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.repository.MessageRepository;
import htw.berlin.WebTech.Chat.Application.repository.TextchannelRepository;
import htw.berlin.WebTech.Chat.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final TextchannelRepository textchannelRepository;

    public Message createMessage(String userId, String channelId, String content){
        Message message = new Message();
        User user = userRepository.findUserById(userId);
        Textchannel textchannel = textchannelRepository.findTextchannelById(channelId);
        message.setId(UUID.randomUUID().toString().substring(0,5));
        message.setSender(user);
        message.setContent(content);
        message.setTextchannel(textchannel);
        message.setDate(java.time.LocalDateTime.now().withNano(0));
        user.getMessages().add(message);
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByTextchannelAndUser(String textchannelId, String userId){
        return messageRepository.findMessagesByTextchannelIdAndSenderId(textchannelId, userId);
    }

    public List<Message> getMessagesByTextchannel(String channelId){
        return messageRepository.findMessagesByTextchannelId(channelId);
    }

}
