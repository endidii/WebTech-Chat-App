package htw.berlin.WebTech.Chat.Application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
    private String content;
    private String date;
    private String sender;
}

