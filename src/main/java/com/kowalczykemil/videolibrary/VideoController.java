package com.kowalczykemil.videolibrary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    @GetMapping("/test")
    public int test() {
        return 1;
    }

}
