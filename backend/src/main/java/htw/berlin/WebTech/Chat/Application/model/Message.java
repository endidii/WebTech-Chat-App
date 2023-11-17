package htw.berlin.WebTech.Chat.Application.model;

import jakarta.persistence.*;
import lombok.Data;
import org.w3c.dom.Text;

import java.nio.channels.Channel;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    private String id;
    private String content;

    @ManyToOne
    private Textchannel textchannel;

    @ManyToOne
    private User sender;
}
