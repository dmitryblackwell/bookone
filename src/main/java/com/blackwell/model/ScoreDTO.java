package com.blackwell.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class ScoreDTO {
    private long isbn;
    private double score;
}
