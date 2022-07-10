package com.tecnotree.ToDo.repository;

import com.tecnotree.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface ToDoRepository connect to db.
 */
@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findByUserIdAndCompleted( Long userId, boolean completed);
}
