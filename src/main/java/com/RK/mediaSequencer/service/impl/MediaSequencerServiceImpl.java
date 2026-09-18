package com.RK.mediaSequencer.service.impl;

import com.RK.mediaSequencer.dto.PlayListRequest;
import com.RK.mediaSequencer.dto.SyncRequest;
import com.RK.mediaSequencer.dto.SyncResponse;
import com.RK.mediaSequencer.model.DisplayWindow;
import com.RK.mediaSequencer.model.Media;
import com.RK.mediaSequencer.model.PlayListItems;
import com.RK.mediaSequencer.model.Sync;
import com.RK.mediaSequencer.repository.DisplayWindowRepo;
import com.RK.mediaSequencer.repository.MediaRepository;
import com.RK.mediaSequencer.repository.PlayListRepository;
import com.RK.mediaSequencer.repository.SyncRepository;
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
    private final SyncRepository syncRepository;

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

    @Override
    @Transactional
    public SyncResponse getSyncState() {
        Sync st = syncRepository.findById(1L).orElse(null);
        if (st == null) return new SyncResponse(false, null, null, null);
        if (st.isActive() && System.currentTimeMillis() >= st.getEndAt()) {
            st.setActive(false);
            syncRepository.save(st);
        }
        return new SyncResponse(st.isActive(), st.getMedia(), st.getStartAt(), st.getEndAt());
    }

    @Override
    @Transactional
    public SyncResponse startSync(SyncRequest request) {
        Media media = mediaRepository.findById(request.getMediaId()).orElseThrow();
        Long duration = request.getDurationInMs() == null || request.getDurationInMs() <= 0 ? 30000 : request.getDurationInMs();
        Long startTime = System.currentTimeMillis();
        Long endTime = startTime + duration;
        Sync s = syncRepository.findById(1L).orElseGet(() -> new Sync());
        s.setMedia(media);
        s.setActive(true);
        s.setStartAt(startTime);
        s.setEndAt(endTime);
        syncRepository.save(s);
        return new SyncResponse(true, media, startTime, endTime);
    }
}
