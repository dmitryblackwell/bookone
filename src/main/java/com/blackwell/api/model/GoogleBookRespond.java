package com.blackwell.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GoogleBookRespond {
    private List<ItemAPIModel> items;
}
