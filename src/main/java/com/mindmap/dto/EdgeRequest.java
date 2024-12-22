package com.mindmap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EdgeRequest {
    private Long sourceNoteId;
    private Long targetNoteId;
    private String type;
    private String style;
} 