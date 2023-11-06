package htw.berlin.WebTech.Chat.Application.controller;

import htw.berlin.WebTech.Chat.Application.model.Message;
import htw.berlin.WebTech.Chat.Application.service.ChatAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ChatAppController {

    private final ChatAppService chatAppService;

    @PostMapping("/api/message")
    public Message createMessage(@RequestBody Message message){return chatAppService.createMessage(message);}

    @GetMapping("/api/message")
    public List<Message> findAll(@RequestParam(required = false)String content){
        if(content != null){
            return chatAppService.searchByContent(content);
        }
        return chatAppService.getAllMessages();
    }

    @GetMapping("/")
    public String index() {
        return "Willkommen bei der Chat App!";
    }

}
