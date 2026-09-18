package com.RK.mediaSequencer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyncRequest {
    private Long mediaId;
    private Long durationMs;
}
