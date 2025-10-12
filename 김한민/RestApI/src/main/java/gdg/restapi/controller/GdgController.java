package gdg.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GdgController {
    @GetMapping("/gdg")
    public String gdg(){
        return "GDG Backend 화이팅!!";
    }
}
