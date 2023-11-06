package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatAppService {

    private final MessageRepository messageRepository;

    public Message createMessage(Message message){
        message.setId(UUID.randomUUID().toString());
        messageRepository.save(message);
        return message;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public List<Message> searchByContent(String content){return messageRepository.findMessageByContentContaining(content);}

}
