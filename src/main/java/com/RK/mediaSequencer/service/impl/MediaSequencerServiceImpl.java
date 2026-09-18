package com.RK.mediaSequencer.service.impl;

import com.RK.mediaSequencer.dto.PlayListRequest;
import com.RK.mediaSequencer.model.DisplayWindow;
import com.RK.mediaSequencer.model.Media;
import com.RK.mediaSequencer.model.PlayListItems;
import com.RK.mediaSequencer.repository.DisplayWindowRepo;
import com.RK.mediaSequencer.repository.MediaRepository;
import com.RK.mediaSequencer.repository.PlayListRepository;
import com.RK.mediaSequencer.service.MediaSequencerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MediaSequencerServiceImpl implements MediaSequencerService {

    private final MediaRepository mediaRepository;
    private final DisplayWindowRepo displayWindowRepo;
    private final PlayListRepository playListRepository;

    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    @Override
    public List<DisplayWindow> getAllWindows() {
        return displayWindowRepo.findAll();
    }

    @Override
    public List<PlayListItems> getPlayList(Long id) {
        return playListRepository.findByWindowIdOrderByPositionAsc(id);
    }

    @Override
    @Transactional
    public PlayListItems addMedia(Long window, PlayListRequest request) {
        DisplayWindow wind = displayWindowRepo.findById(window).orElseThrow(null);
        Media media = mediaRepository.findById(request.getMediaId()).orElseThrow(null);
        Integer pos = playListRepository.findByWindowIdOrderByPositionAsc(window).size();
        Long duration = (request.getDurationInMs() == null || request.getDurationInMs() == 0) ? 30000: request.getDurationInMs();

        return new PlayListItems(wind, media, pos, duration);
    }
}
