package htw.berlin.WebTech.Chat.Application.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    @Column(nullable = false)
    private String username;
    //@Column(nullable = false)
    private String email;
    //@Column(nullable = false)
    private String password;

    @OneToMany
    private List<Message> messages;

    @ManyToMany
    private List<Textchannel> textchannels;
}
