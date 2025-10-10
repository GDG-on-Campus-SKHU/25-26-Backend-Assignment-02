package com.gdg.restapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GdgController {
    @GetMapping("/gdg")
    public String gdg() {
        return "GDG Backend 모두모두 수료하자!!";
    }
}
