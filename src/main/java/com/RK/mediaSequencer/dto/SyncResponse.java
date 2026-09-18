package com.RK.mediaSequencer.dto;

import com.RK.mediaSequencer.model.Media;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncResponse {
    private boolean active;
    private Media media;
    private Long startAt;
    private Long endAt;
}
