package com.mindmap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteRequest {
    private String content;
    private String color;
    private Double positionX;
    private Double positionY;
} 