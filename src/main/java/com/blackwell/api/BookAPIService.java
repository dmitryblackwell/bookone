package com.blackwell.api;

import com.blackwell.api.model.ItemAPIModel;

import java.util.List;

public interface BookAPIService {
    public List<ItemAPIModel> searchBookList(String searchText, Integer startIndex);
}
