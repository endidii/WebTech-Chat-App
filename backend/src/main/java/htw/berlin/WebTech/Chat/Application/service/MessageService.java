package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.model.User;
import htw.berlin.WebTech.Chat.Application.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message createMessage(Message message){
        message.setId(UUID.randomUUID().toString().substring(0,5));
        messageRepository.save(message);
        return message;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public List<Message> searchByContent(String content){
        return messageRepository.findMessageByContentContaining(content);
    }

    public List<Message> getMessagesByUser_Username(String username) {
        return messageRepository.findByUser_Username(username);
    }

}
