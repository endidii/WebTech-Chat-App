package htw.berlin.WebTech.Chat.Application.repository;

import htw.berlin.WebTech.Chat.Application.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findMessagesByTextchannelIdAndSenderId(String textchannelId, String userId);
    List<Message> findMessagesByTextchannelId(String channelId);
}
