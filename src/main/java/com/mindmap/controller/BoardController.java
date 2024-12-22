package com.mindmap.controller;

import com.mindmap.dto.BoardRequest;
import com.mindmap.entity.Board;
import com.mindmap.entity.User;
import com.mindmap.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<Board>> getUserBoards(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(boardService.getUserBoards(user.getId()));
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(
        @RequestBody BoardRequest request,
        @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(boardService.createBoard(request.getTitle(), user.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(
        @PathVariable Long id,
        @RequestBody BoardRequest request,
        @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(boardService.updateBoard(id, request.getTitle(), user.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(
        @PathVariable Long id,
        @AuthenticationPrincipal User user
    ) {
        boardService.deleteBoard(id, user.getId());
        return ResponseEntity.ok().build();
    }
} 