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
    private Textchannel textchannel;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Directchannel directchannel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Textchannel getTextchannel() {
        return textchannel;
    }

    public void setTextchannel(Textchannel textchannel) {
        this.textchannel = textchannel;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Directchannel getDirectchannel() {
        return directchannel;
    }

    public void setDirectchannel(Directchannel directchannel) {
        this.directchannel = directchannel;
    }
}
