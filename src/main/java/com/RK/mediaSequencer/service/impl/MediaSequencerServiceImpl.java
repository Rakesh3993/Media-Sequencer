package com.RK.mediaSequencer.service.impl;

import com.RK.mediaSequencer.model.Media;
import com.RK.mediaSequencer.repository.MediaRepository;
import com.RK.mediaSequencer.service.MediaSequencerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MediaSequencerServiceImpl implements MediaSequencerService {

    private final MediaRepository mediaRepository;

    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }
}
