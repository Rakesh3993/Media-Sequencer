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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MediaSequencerServiceImpl implements MediaSequencerService {

    private final MediaRepository mediaRepository;
    private final DisplayWindowRepo displayWindowRepo;
    private final PlayListRepository playListRepository;
    private final SyncRepository syncRepository;
    private final SimpMessagingTemplate ws;

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

        ws.convertAndSend("/topic/window/" + window + "/playlist" + getPlayList(window));
        ws.convertAndSend("/topic/windows", "PLAYLIST_UPDATED");

        return new PlayListItems(wind, media, pos, duration);
    }

    @Override
    @Transactional
    public SyncResponse getSyncState() {
        Sync st = syncRepository.findById(1L).orElse(null);
        if (st == null) return new SyncResponse(false, null, null, null);
        if (st.isActive() && System.currentTimeMillis() >= st.getEndsAtEpochMs()) {
            st.setActive(false);
            syncRepository.save(st);
        }
        return new SyncResponse(st.isActive(), st.getMedia(), st.getStartedAtEpochMs(), st.getEndsAtEpochMs());
    }

    @Override
    @Transactional
    public SyncResponse startSync(SyncRequest request) {
        Media media = mediaRepository.findById(request.getMediaId()).orElseThrow();
        Long duration = request.getDurationMs() == null || request.getDurationMs() <= 0 ? 10000 : request.getDurationMs();
        Long startTime = System.currentTimeMillis();
        Long endTime = startTime + duration;
        Sync s = syncRepository.findById(1L).orElseGet(() -> new Sync());
        s.setMedia(media);
        s.setActive(true);
        s.setStartedAtEpochMs(startTime);
        s.setEndsAtEpochMs(endTime);
        syncRepository.save(s);
        SyncResponse output = new SyncResponse(true, media, startTime, endTime);
        ws.convertAndSend("/topic/sync", output);
        return output;
    }
}
