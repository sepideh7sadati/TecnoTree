package com.tecnotree.comment.reopsitory;

import com.tecnotree.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface CommentRepository connect to db.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);
}
