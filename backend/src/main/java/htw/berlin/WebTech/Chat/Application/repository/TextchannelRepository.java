package htw.berlin.WebTech.Chat.Application.repository;

import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextchannelRepository extends JpaRepository<Textchannel, String> {
    Textchannel findTextchannelById(String id);
    List<Textchannel> findTextchannelsByUsersId(String userId);
}
