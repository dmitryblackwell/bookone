package com.blackwell.web;

import com.blackwell.entity.Author;
import com.blackwell.model.SuggestionDTO;
import com.blackwell.repository.AuthorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/search")
    @ResponseBody
    public List<SuggestionDTO> searchAuthors(String term) {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .filter(a -> StringUtils.containsIgnoreCase(a.getFullName(), term))
                .map(author -> new SuggestionDTO(author.getFullName(), String.valueOf(author.getId())))
                .collect(Collectors.toList());
    }

}
