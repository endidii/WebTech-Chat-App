package htw.berlin.WebTech.Chat.Application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Directchannel {
    @Id
    String id;

    @ManyToMany
    List<User> users;

    @OneToMany
    private List<Message> messages;
}
