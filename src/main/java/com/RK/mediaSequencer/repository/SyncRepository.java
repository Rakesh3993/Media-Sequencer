package com.RK.mediaSequencer.repository;

import com.RK.mediaSequencer.model.Sync;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyncRepository extends JpaRepository<Sync, Long> {
}
