package com.mindmap.repository;

import com.mindmap.entity.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {
    List<Edge> findByBoardId(Long boardId);
    void deleteBySourceNoteIdOrTargetNoteId(Long noteId, Long noteId2);
} 