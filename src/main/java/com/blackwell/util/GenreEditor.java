package com.blackwell.util;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blackwell.entity.Genre;
import com.blackwell.service.BookService;

@Component
public class GenreEditor extends PropertyEditorSupport {
	
	@Autowired
	private BookService service;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Genre genre = service.getGenre(text);
		setValue(genre);
	}
	
	@Override
	public String getAsText() {
		Genre g = (Genre) getValue();
		return g == null ? "" : g.getName();
	}
}
