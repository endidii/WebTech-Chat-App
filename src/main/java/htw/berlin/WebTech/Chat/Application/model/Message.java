package htw.berlin.WebTech.Chat.Application.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    String id;
    String content;

    @ManyToOne
    private User user;
}
