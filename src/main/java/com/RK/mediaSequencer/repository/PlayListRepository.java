package com.RK.mediaSequencer.repository;

import com.RK.mediaSequencer.model.PlayListItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayListItems, Long> {
    List<PlayListItems> findByWindowIdOrderByPositionAsc(Long windowId);
}
