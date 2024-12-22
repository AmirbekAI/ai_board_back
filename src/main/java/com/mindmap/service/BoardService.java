package com.mindmap.service;

import com.mindmap.entity.Board;
import com.mindmap.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserService userService;

    public List<Board> getUserBoards(Long userId) {
        return boardRepository.findByOwnerId(userId);
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Board not found"));
    }

    public Board createBoard(String title, Long userId) {
        Board board = new Board();
        board.setTitle(title);
        board.setOwner(userService.getUserById(userId));
        return boardRepository.save(board);
    }

    public Board updateBoard(Long id, String title, Long userId) {
        Board board = getBoardById(id);
        
        if (!board.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        board.setTitle(title);
        return boardRepository.save(board);
    }

    public void deleteBoard(Long id, Long userId) {
        Board board = getBoardById(id);
        
        if (!board.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        boardRepository.delete(board);
    }
} 