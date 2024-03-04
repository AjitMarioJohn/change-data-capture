package com.cdc.data.change.controllers;

import com.cdc.data.change.services.WikimediaService;
import com.cdc.data.model.repositories.WikimediaEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WikimediaController {

    private final WikimediaService wikimediaService;
    private final WikimediaEventRepository wikimediaEventRepository;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("welcome");
    }

    @GetMapping("/start/streaming")
    public ResponseEntity<String> start() {
        wikimediaService.startStreaming();
        return ResponseEntity.ok("Streaming started");
    }

    @GetMapping("/stop/streaming")
    public ResponseEntity<String> stop() {
        wikimediaService.stopStreaming();
        return ResponseEntity.ok("Streaming stopped");
    }
}
