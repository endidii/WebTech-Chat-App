package htw.berlin.WebTech.Chat.Application.repository;

import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    User findUserById(String id);
}
