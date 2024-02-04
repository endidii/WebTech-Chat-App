package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.dto.MessageDTO;
import htw.berlin.WebTech.Chat.Application.dto.UserDTO;
import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.repository.MessageRepository;
import htw.berlin.WebTech.Chat.Application.repository.TextchannelRepository;
import htw.berlin.WebTech.Chat.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final TextchannelRepository textchannelRepository;
    final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public Message createMessage(String userId, String channelId, String content){
        Message message = new Message();
        User user = userRepository.findUserById(userId);
        Textchannel textchannel = textchannelRepository.findTextchannelById(channelId);
        message.setId(UUID.randomUUID().toString().substring(0,5));
        message.setSender(user);
        message.setContent(content);
        message.setTextchannel(textchannel);
        LocalDateTime ldt = LocalDateTime.now();
        String formattedString = ldt.format(CUSTOM_FORMATTER);
        message.setDate(formattedString);
        user.getMessages().add(message);
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByTextchannelAndUser(String textchannelId, String userId){
        return messageRepository.findMessagesByTextchannelIdAndSenderId(textchannelId, userId);
    }

    public List<MessageDTO> getMessagesByTextchannel(String channelId){
        Textchannel textchannel = textchannelRepository.findTextchannelById(channelId);
        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (Message message : textchannel.getMessages()) {
            MessageDTO dto = new MessageDTO();
            UserDTO senderDTO = new UserDTO();
            senderDTO.setUsername(message.getSender().getUsername());
            dto.setSender(senderDTO);
            dto.setContent(message.getContent());
            dto.setDate(message.getDate());
            messageDTOs.add(dto);
        }
        return messageDTOs;
    }

    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }

}
