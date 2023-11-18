package htw.berlin.WebTech.Chat.Application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    private String id;
    private String content;

    @ManyToOne
    @JsonBackReference
    private Textchannel textchannel;

    @ManyToOne
    @JsonBackReference
    private User sender;
}
