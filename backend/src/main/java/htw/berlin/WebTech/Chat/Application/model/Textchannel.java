package htw.berlin.WebTech.Chat.Application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "textchannel")
public class Textchannel {
    @Id
    private String id;
    private String name;
    private String description;

    @ManyToMany
    private List<User> users;

    @OneToMany
    private List<Message> messages;
}
