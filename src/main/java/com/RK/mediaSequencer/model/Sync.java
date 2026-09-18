package com.RK.mediaSequencer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sync {

    @Id
    private Long id = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "media_id")
    private Media media;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private Long startAt;

    @Column(nullable = false)
    private Long endAt;
}
