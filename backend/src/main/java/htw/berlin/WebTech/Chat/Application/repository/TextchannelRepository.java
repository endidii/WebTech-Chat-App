package htw.berlin.WebTech.Chat.Application.repository;

import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextchannelRepository extends JpaRepository<Textchannel, String> {
    Textchannel findTextchannelById(String id);
}
