package com.BHoussem.redditCloneAPI.repository;

import com.BHoussem.redditCloneAPI.model.Comment;
import com.BHoussem.redditCloneAPI.model.Post;
import com.BHoussem.redditCloneAPI.model.Redditor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(Redditor user);
}

