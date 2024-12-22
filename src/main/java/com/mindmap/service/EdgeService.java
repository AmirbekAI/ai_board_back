package com.mindmap.service;

import com.mindmap.dto.EdgeRequest;
import com.mindmap.entity.Board;
import com.mindmap.entity.Edge;
import com.mindmap.entity.Note;
import com.mindmap.repository.EdgeRepository;
import com.mindmap.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EdgeService {
    private final EdgeRepository edgeRepository;
    private final NoteRepository noteRepository;
    private final BoardService boardService;

    public List<Edge> getBoardEdges(Long boardId, Long userId) {
        validateBoardAccess(boardId, userId);
        return edgeRepository.findByBoardId(boardId);
    }

    public Edge createEdge(Long boardId, EdgeRequest request, Long userId) {
        Board board = validateBoardAccess(boardId, userId);
        
        Note sourceNote = noteRepository.findById(request.getSourceNoteId())
            .orElseThrow(() -> new RuntimeException("Source note not found"));
        Note targetNote = noteRepository.findById(request.getTargetNoteId())
            .orElseThrow(() -> new RuntimeException("Target note not found"));

        Edge edge = new Edge();
        edge.setBoard(board);
        edge.setSourceNote(sourceNote);
        edge.setTargetNote(targetNote);
        edge.setType(request.getType());
        edge.setStyle(request.getStyle());
        
        return edgeRepository.save(edge);
    }

    public void deleteEdge(Long boardId, Long edgeId, Long userId) {
        validateBoardAccess(boardId, userId);
        edgeRepository.deleteById(edgeId);
    }

    private Board validateBoardAccess(Long boardId, Long userId) {
        Board board = boardService.getBoardById(boardId);
        if (!board.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to board");
        }
        return board;
    }
} 