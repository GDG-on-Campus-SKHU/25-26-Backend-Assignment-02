package gdg.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GdgController {
    @GetMapping("/gdg")
    public String gdg(){
        return "gdg 친구들";
    }
}
