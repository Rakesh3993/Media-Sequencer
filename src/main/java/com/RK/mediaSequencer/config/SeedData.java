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
            Media m1 = mediaRepo.save(
                    new Media(
                            "[Video] Cloud Computing",
                            MediaType.VIDEO,
                            "https://d2j2uxe7jasn0r.cloudfront.net/watermarks/video/SZe0QQ5rcl2l9mvmt/videoblocks-cloud-computing-server-room_be_9smfxn__fd6eb3af73816eb275d22f8e3e57a703__P360.mp4"
                    )
            );

            Media m2 = mediaRepo.save(
                    new Media(
                            "Code on Screen",
                            MediaType.IMAGE,
                            "https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=1200"
                    )
            );

            Media m3 = mediaRepo.save(
                    new Media(
                            "[Video] Programming",
                            MediaType.VIDEO,
                            "https://pixabay.com/videos/download/video-52823_medium.mp4"
                    )
            );

            Media m4 = mediaRepo.save(
                    new Media(
                            "[Image] Database Server",
                            MediaType.IMAGE,
                            "https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=1200"
                    )
            );
            Media blank = mediaRepo.save(new Media("Blank", MediaType.BLANK, ""));

            DisplayWindow w1 = windowRepo.save(new DisplayWindow("Window 1"));
            DisplayWindow w2 = windowRepo.save(new DisplayWindow("Window 2"));
            DisplayWindow w3 = windowRepo.save(new DisplayWindow("Window 3"));
            DisplayWindow w4 = windowRepo.save(new DisplayWindow("Window 4"));

            Long duration = 10000L;

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
