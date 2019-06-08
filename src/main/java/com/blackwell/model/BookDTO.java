package com.blackwell.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Value;

import java.util.List;

@Builder(toBuilder = true)
@Value
public class BookDTO {
    private long isbn;
    private String name;
    private Double score;
    private float price;
    private String description;
    private List<String> genres;
    private List<String> authors;
}
