package com.RK.mediaSequencer.controller;

import com.RK.mediaSequencer.model.Media;
import com.RK.mediaSequencer.service.MediaSequencerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaSequencerService mediaSequencerService;

    @GetMapping
    public ResponseEntity<List<Media>> getMedia() {
        List<Media> medias = mediaSequencerService.getAllMedia();
        return new ResponseEntity<>(medias, HttpStatus.OK);
    }
}
