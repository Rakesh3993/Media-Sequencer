package com.RK.mediaSequencer.repository;

import com.RK.mediaSequencer.model.PlayListItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListRepository extends JpaRepository<PlayListItems, Long> {
    List<PlayListItems> findByWindowIdOrderByPositionAsc(Long windowId);
}
