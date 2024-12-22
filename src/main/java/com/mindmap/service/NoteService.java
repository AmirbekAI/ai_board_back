package com.mindmap.service;

import com.mindmap.dto.NoteRequest;
import com.mindmap.entity.Board;
import com.mindmap.entity.Note;
import com.mindmap.repository.NoteRepository;
import com.mindmap.repository.EdgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final BoardService boardService;
    private final EdgeRepository edgeRepository;

    public List<Note> getBoardNotes(Long boardId, Long userId) {
        validateBoardAccess(boardId, userId);
        return noteRepository.findByBoardId(boardId);
    }

    public Note createNote(Long boardId, NoteRequest request, Long userId) {
        Board board = validateBoardAccess(boardId, userId);
        
        Note note = new Note();
        note.setBoard(board);
        note.setContent(request.getContent());
        note.setColor(request.getColor());
        note.setPositionX(request.getPositionX());
        note.setPositionY(request.getPositionY());
        
        return noteRepository.save(note);
    }

    public Note updateNote(Long boardId, Long noteId, NoteRequest request, Long userId) {
        validateBoardAccess(boardId, userId);
        
        Note note = noteRepository.findById(noteId)
            .orElseThrow(() -> new RuntimeException("Note not found"));
        
        note.setContent(request.getContent());
        note.setColor(request.getColor());
        note.setPositionX(request.getPositionX());
        note.setPositionY(request.getPositionY());
        
        return noteRepository.save(note);
    }

    @Transactional
    public void deleteNote(Long boardId, Long noteId, Long userId) {
        validateBoardAccess(boardId, userId);
        
        // Delete associated edges first
        edgeRepository.deleteBySourceNoteIdOrTargetNoteId(noteId, noteId);
        noteRepository.deleteById(noteId);
    }

    private Board validateBoardAccess(Long boardId, Long userId) {
        Board board = boardService.getBoardById(boardId);
        if (!board.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to board");
        }
        return board;
    }
} 