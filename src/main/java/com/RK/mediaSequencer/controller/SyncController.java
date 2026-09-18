package com.RK.mediaSequencer.controller;

import com.RK.mediaSequencer.dto.SyncRequest;
import com.RK.mediaSequencer.dto.SyncResponse;
import com.RK.mediaSequencer.service.MediaSequencerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sync")
public class SyncController {

    private final MediaSequencerService mediaSequencerService;

    @GetMapping
    public ResponseEntity<SyncResponse> getSyncState() {
        SyncResponse response = mediaSequencerService.getSyncState();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SyncResponse> sync(@RequestBody SyncRequest request) {
        SyncResponse response = mediaSequencerService.startSync(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
