package com.RK.mediaSequencer.config;

import com.RK.mediaSequencer.model.DisplayWindow;
import com.RK.mediaSequencer.model.Media;
import com.RK.mediaSequencer.model.MediaType;
import com.RK.mediaSequencer.model.PlayListItems;
import com.RK.mediaSequencer.repository.DisplayWindowRepo;
import com.RK.mediaSequencer.repository.MediaRepository;
import com.RK.mediaSequencer.repository.PlayListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedData {

    @Bean
    CommandLineRunner seed(
        MediaRepository mediaRepo,
        DisplayWindowRepo windowRepo,
        PlayListRepository playListRepo

    ) {
        return args -> {
            if(mediaRepo.count() > 0 || windowRepo.count() > 0 || playListRepo.count() > 0) return;
            Media m1 = mediaRepo.save(new Media("M1-Video", MediaType.VIDEO, "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"));
            Media m2 = mediaRepo.save(new Media("M2-Image", MediaType.IMAGE, "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?w=1200"));
            Media m3 = mediaRepo.save(new Media("M3-Video", MediaType.VIDEO, "https://pixabay.com/videos/download/video-337626_medium.mp4"));
            Media m4 = mediaRepo.save(new Media("M4-Image", MediaType.IMAGE, "https://images.unsplash.com/photo-1470770841072-f978cf4d019e?w=1200"));
            Media blank = mediaRepo.save(new Media("Blank", MediaType.BLANK, ""));

            DisplayWindow w1 = windowRepo.save(new DisplayWindow("Window 1"));
            DisplayWindow w2 = windowRepo.save(new DisplayWindow("Window 2"));
            DisplayWindow w3 = windowRepo.save(new DisplayWindow("Window 3"));
            DisplayWindow w4 = windowRepo.save(new DisplayWindow("Window 4"));

            Long duration = 60000L;

            playListRepo.save(new PlayListItems(w1, m1, 0, duration));
            playListRepo.save(new PlayListItems(w1, m2, 1, duration));
            playListRepo.save(new PlayListItems(w1, m3, 2, duration));
            playListRepo.save(new PlayListItems(w1, m4, 3, duration));
            playListRepo.save(new PlayListItems(w1, blank, 4, duration));

            playListRepo.save(new PlayListItems(w2, m2, 0, duration));
            playListRepo.save(new PlayListItems(w2, m3, 1, duration));
            playListRepo.save(new PlayListItems(w2, m4, 2, duration));
            playListRepo.save(new PlayListItems(w2, blank, 3, duration));
            playListRepo.save(new PlayListItems(w2, m1, 4, duration));

            playListRepo.save(new PlayListItems(w3, m3, 0, duration));
            playListRepo.save(new PlayListItems(w3, m4, 1, duration));
            playListRepo.save(new PlayListItems(w3, blank, 2, duration));
            playListRepo.save(new PlayListItems(w3, m1, 3, duration));
            playListRepo.save(new PlayListItems(w3, m2, 4, duration));

            playListRepo.save(new PlayListItems(w4, m4, 0, duration));
            playListRepo.save(new PlayListItems(w4, blank, 1, duration));
            playListRepo.save(new PlayListItems(w4, m1, 2, duration));
            playListRepo.save(new PlayListItems(w4, m2, 3, duration));
            playListRepo.save(new PlayListItems(w4, m3, 4, duration));

        };
    }
}
