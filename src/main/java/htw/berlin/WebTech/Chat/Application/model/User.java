package htw.berlin.WebTech.Chat.Application.model;

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
    private String password;
    //@Column(nullable = false)
    private String email;

    @OneToMany
    private List<Message> messages;
}
