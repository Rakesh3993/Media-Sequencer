package com.RK.mediaSequencer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PlayListItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "window_id")
    private DisplayWindow displayWindow;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "media_id")
    private Media media;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false)
    private Long durationInMs;

    public PlayListItems(DisplayWindow displayWindow, Media media, Integer position, Long durationInMs) {
        this.displayWindow = displayWindow;
        this.media = media;
        this.position = position;
        this.durationInMs = durationInMs;
    }
}
