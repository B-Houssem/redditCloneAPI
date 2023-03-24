package com.BHoussem.redditCloneAPI.repository;

import com.BHoussem.redditCloneAPI.model.Post;
import com.BHoussem.redditCloneAPI.model.Redditor;
import com.BHoussem.redditCloneAPI.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, Redditor currentUser);
}