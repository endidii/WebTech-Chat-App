package htw.berlin.WebTech.Chat.Application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatAppController {

    @GetMapping("/")
    public String index() {
        return "Willkommen bei der Chat App!";
    }

}
