package com.blackwell.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IsbnAPIModel {
    private String type;
    private String identifier;
}
