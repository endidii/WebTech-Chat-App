package htw.berlin.WebTech.Chat.Application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Message {
    @Id
    String id;
    String content;
}
