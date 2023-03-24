package com.BHoussem.redditCloneAPI.repository;

import com.BHoussem.redditCloneAPI.model.Post;
import com.BHoussem.redditCloneAPI.model.Subreddit;
import com.BHoussem.redditCloneAPI.model.Redditor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(Redditor user);
}
