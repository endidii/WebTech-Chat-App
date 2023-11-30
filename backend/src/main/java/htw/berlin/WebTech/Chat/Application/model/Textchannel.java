package htw.berlin.WebTech.Chat.Application.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "textchannel")
public class Textchannel {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String description;

    @ManyToMany
    private List<User> users;

    @OneToMany
    private List<Message> messages;
}
