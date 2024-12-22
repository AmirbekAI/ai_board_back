package com.mindmap.controller;

import com.mindmap.dto.NoteRequest;
import com.mindmap.entity.Note;
import com.mindmap.entity.User;
import com.mindmap.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getBoardNotes(
        @PathVariable Long boardId,
        @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(noteService.getBoardNotes(boardId, user.getId()));
    }

    @PostMapping
    public ResponseEntity<Note> createNote(
        @PathVariable Long boardId,
        @RequestBody NoteRequest request,
        @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(noteService.createNote(boardId, request, user.getId()));
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNote(
        @PathVariable Long boardId,
        @PathVariable Long noteId,
        @RequestBody NoteRequest request,
        @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(noteService.updateNote(boardId, noteId, request, user.getId()));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<?> deleteNote(
        @PathVariable Long boardId,
        @PathVariable Long noteId,
        @AuthenticationPrincipal User user
    ) {
        noteService.deleteNote(boardId, noteId, user.getId());
        return ResponseEntity.ok().build();
    }
} 