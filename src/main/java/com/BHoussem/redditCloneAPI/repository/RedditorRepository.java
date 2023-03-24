package com.BHoussem.redditCloneAPI.repository;

import com.BHoussem.redditCloneAPI.model.Redditor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RedditorRepository extends JpaRepository<Redditor, Long> {
    Optional<Redditor> findByUsername(String username);
}
