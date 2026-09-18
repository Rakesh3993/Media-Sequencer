package com.RK.mediaSequencer.service;

import com.RK.mediaSequencer.dto.PlayListRequest;
import com.RK.mediaSequencer.dto.SyncRequest;
import com.RK.mediaSequencer.dto.SyncResponse;
import com.RK.mediaSequencer.model.DisplayWindow;
import com.RK.mediaSequencer.model.Media;
import com.RK.mediaSequencer.model.PlayListItems;

import java.util.List;

public interface MediaSequencerService {
    List<Media> getAllMedia();
    List<DisplayWindow> getAllWindows();
    List<PlayListItems> getPlayList(Long id);
    PlayListItems addMedia(Long window, PlayListRequest request);
    SyncResponse getSyncState();
    SyncResponse startSync(SyncRequest request);
}
