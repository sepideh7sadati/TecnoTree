package com.tecnotree.post.repository;

import com.tecnotree.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface PostRepository connect to db.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT po FROM Post  po where po.title like %:title% ")
    List<Post> findPostByTitle(String title);
}
