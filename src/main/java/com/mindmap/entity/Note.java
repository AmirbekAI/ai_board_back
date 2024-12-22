package com.mindmap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(columnDefinition = "TEXT")
    private String content;
    
    private String color;
    private Double positionX;
    private Double positionY;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_edited")
    private LocalDateTime lastEdited;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        lastEdited = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastEdited = LocalDateTime.now();
    }
} 