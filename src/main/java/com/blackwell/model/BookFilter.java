package com.blackwell.model;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

@Getter
public class BookFilter {
    private Integer pageNo;
    private String sortColumn;
    private String searchValue;
    private List<String> genresNames;

    public BookFilter(Integer pageNo, String sortColumn, String searchValue, List<String> genresNames) {
        setPageNo(pageNo);
        setSortColumn(sortColumn);
        setSearchValue(searchValue);
        setGenresNames(genresNames);
    }

    public void setPageNo(final Integer pageNo) {
        boolean isNotValid = pageNo == null;
        this.pageNo = isNotValid ? 0 : pageNo;
    }

    public void setSortColumn(final String sortColumn) {
        boolean isNotValid = StringUtils.isBlank(sortColumn) || "null".equals(sortColumn);
        this.sortColumn = isNotValid ? "isbn" : sortColumn;
    }

    public void setSearchValue(String searchValue) {
        boolean isNotValid = StringUtils.isBlank(searchValue) || "null".equals(searchValue);
        this.searchValue = isNotValid ? null : searchValue;
    }

    public void setGenresNames(List<String> genresNames) {
        this.genresNames = genresNames;
    }
}
