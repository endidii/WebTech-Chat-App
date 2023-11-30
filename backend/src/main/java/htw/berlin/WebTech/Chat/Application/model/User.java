package htw.berlin.WebTech.Chat.Application.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;

    @Column(nullable = false)
    @NotNull
    private String username;

    @Column(nullable = false)
    @Email
    @NotNull
    private String email;

    @Column(nullable = false)
    @Size(min = 8)
    @NotNull
    private String password;

    @OneToMany
    private List<Message> messages;

    @ManyToMany
    private List<Textchannel> textchannels;

    @ManyToMany
    private List<Directchannel> directchannels;
}
