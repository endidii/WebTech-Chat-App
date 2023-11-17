package htw.berlin.WebTech.Chat.Application.service;

import htw.berlin.WebTech.Chat.Application.model.Textchannel;
import htw.berlin.WebTech.Chat.Application.repository.TextchannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TextchannelService {
    private final TextchannelRepository textchannelRepository;

    public Textchannel createTextchannel(Textchannel textchannel){
        textchannel.setId(UUID.randomUUID().toString().substring(0,5));
        return textchannelRepository.save(textchannel);
    }

    public List<Textchannel> getAllTextchannels() {
        return textchannelRepository.findAll();
    }


}
