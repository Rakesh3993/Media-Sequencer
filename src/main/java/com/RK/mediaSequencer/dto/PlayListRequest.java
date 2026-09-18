package com.RK.mediaSequencer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayListRequest {
    private Long mediaId;
    private Long durationInMs;
}
