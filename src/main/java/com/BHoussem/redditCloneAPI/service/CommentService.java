package com.BHoussem.redditCloneAPI.service;

import com.BHoussem.redditCloneAPI.dto.CommentsDto;
import com.BHoussem.redditCloneAPI.exceptions.PostNotFoundException;
import com.BHoussem.redditCloneAPI.exceptions.SpringRedditException;
import com.BHoussem.redditCloneAPI.mapper.CommentMapper;
import com.BHoussem.redditCloneAPI.model.Comment;
import com.BHoussem.redditCloneAPI.model.NotificationEmail;
import com.BHoussem.redditCloneAPI.model.Post;
import com.BHoussem.redditCloneAPI.model.Redditor;
import com.BHoussem.redditCloneAPI.repository.CommentRepository;
import com.BHoussem.redditCloneAPI.repository.PostRepository;
import com.BHoussem.redditCloneAPI.repository.RedditorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final RedditorRepository redditorRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, Redditor user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).toList();
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        Redditor user = redditorRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .toList();
    }

    public boolean containsSwearWords(String comment) {
        if (comment.contains("shit")) {
            throw new SpringRedditException("Comments contains unacceptable language");
        }
        return false;
    }
}

