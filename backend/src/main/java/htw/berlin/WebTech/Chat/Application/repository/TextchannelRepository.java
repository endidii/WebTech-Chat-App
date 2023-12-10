package htw.berlin.WebTech.Chat.Application.repository;

import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextchannelRepository extends JpaRepository<Textchannel, String> {
    Textchannel findTextchannelById(String id);
    List<Textchannel> findTextchannelsByUsersId(String userId);
    @Query("SELECT tc.users FROM Textchannel tc WHERE tc.id = :textChannelId")
    List<User> findUsersByChannelId(@Param("textChannelId") String textChannelId);
    Textchannel findTextchannelByName(String name);
}
