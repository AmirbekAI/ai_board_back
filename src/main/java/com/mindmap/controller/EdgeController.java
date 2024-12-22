package com.mindmap.controller;

import com.mindmap.dto.EdgeRequest;
import com.mindmap.entity.Edge;
import com.mindmap.entity.User;
import com.mindmap.service.EdgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/edges")
@RequiredArgsConstructor
public class EdgeController {
    private final EdgeService edgeService;

    @GetMapping
    public ResponseEntity<List<Edge>> getBoardEdges(
        @PathVariable Long boardId,
        @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(edgeService.getBoardEdges(boardId, user.getId()));
    }

    @PostMapping
    public ResponseEntity<Edge> createEdge(
        @PathVariable Long boardId,
        @RequestBody EdgeRequest request,
        @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(edgeService.createEdge(boardId, request, user.getId()));
    }

    @DeleteMapping("/{edgeId}")
    public ResponseEntity<?> deleteEdge(
        @PathVariable Long boardId,
        @PathVariable Long edgeId,
        @AuthenticationPrincipal User user
    ) {
        edgeService.deleteEdge(boardId, edgeId, user.getId());
        return ResponseEntity.ok().build();
    }
} 