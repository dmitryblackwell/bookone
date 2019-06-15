package com.blackwell.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class VolumeInfoModel {
    private String title;
    private Set<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<IsbnAPIModel> industryIdentifiers;
    private int pageCount;
    private List<String> categories;
    private double averageRating;
    private ImageLinksAPIModel imageLinks;
}
