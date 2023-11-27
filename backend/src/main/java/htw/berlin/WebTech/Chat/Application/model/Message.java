package htw.berlin.WebTech.Chat.Application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import htw.berlin.WebTech.Chat.Application.service.DirectchannelService;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    private String id;
    private String content;
    private LocalDateTime date;

    @ManyToOne
    @JsonBackReference
    private Textchannel textchannel;

    @ManyToOne
    @JsonBackReference
    private User sender;

    @ManyToOne
    @JsonBackReference
    private Directchannel directchannel;
}
