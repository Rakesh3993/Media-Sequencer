package com.RK.mediaSequencer.controller;

import com.RK.mediaSequencer.dto.PlayListRequest;
import com.RK.mediaSequencer.model.DisplayWindow;
import com.RK.mediaSequencer.model.PlayListItems;
import com.RK.mediaSequencer.service.MediaSequencerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/windows")
@RequiredArgsConstructor
public class DisplayWindowController {

    private final MediaSequencerService mediaSequencerService;

    @GetMapping
    public ResponseEntity<List<DisplayWindow>> getWindows() {
        List<DisplayWindow> windows = mediaSequencerService.getAllWindows();
        return new ResponseEntity<>(windows, HttpStatus.OK);
    }

    @GetMapping("/{id}/playlist")
    public ResponseEntity<List<PlayListItems>> playList(@PathVariable Long id) {
        List<PlayListItems> playListItems = mediaSequencerService.getPlayList(id);
        return new ResponseEntity<>(playListItems, HttpStatus.OK);
    }

    @PostMapping("/{id}/playlist")
    public ResponseEntity<PlayListItems> add(@PathVariable Long window, @RequestBody PlayListRequest request) {
        PlayListItems playListItem = mediaSequencerService.addMedia(window, request);
        return new ResponseEntity<>(playListItem, HttpStatus.CREATED);
    }
}
