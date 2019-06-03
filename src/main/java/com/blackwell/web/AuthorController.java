package com.blackwell.web;

import com.blackwell.entity.Author;
import com.blackwell.repository.AuthorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/search")
    public List<Author> searchAuthors(String name) {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .filter(author -> StringUtils.equalsIgnoreCase(author.getFullName(), name))
                .collect(Collectors.toList());
    }
}
