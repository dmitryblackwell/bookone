package com.blackwell.api;

import com.blackwell.api.model.ItemAPIModel;
import com.blackwell.api.model.VolumeInfoModel;
import com.blackwell.entity.Author;
import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.repository.BookRepository;
import com.blackwell.repository.GenreRepository;
import com.blackwell.util.ServiceUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class BookAPIController {

    @Autowired
    BookRepository bookRepository;;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    BookAPIService bookAPIService;

    @GetMapping("/update")
    @ResponseBody
    public String updateLibrary() {
        List<Genre> genres = ServiceUtils.getListFromIterable(genreRepository.findAll());
        for (Genre genre : genres) {
            for (int i = 0; i < 100; ++i) {
                String search = "subject:" + genre.getName();
                try {
                    List<ItemAPIModel> models = bookAPIService.searchBookList(search, i);
                    saveBooks(models);
                    System.out.println(search + ":" + i);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("!" + search + ":" + i);
                }
            }
        }
        return "<a href='/book'>books</a>";
    }

    private void saveBooks(List<ItemAPIModel> models) {
        for(ItemAPIModel itemAPIModel : models) {
            VolumeInfoModel volumeInfo = itemAPIModel.getVolumeInfo();
            Set<Author> authors = new HashSet<>();
            for (String authorName : volumeInfo.getAuthors())
                authors.add(Author.builder().fullName(authorName).build());
            Set<Genre> genres = new HashSet<>();
            for (String genresNames : CollectionUtils.emptyIfNull(volumeInfo.getCategories())) {
                Genre g = new Genre();
                g.setName(genresNames);
                genres.add(g);
            }
            Long isbn;
            try {
                isbn = Long.valueOf(volumeInfo.getIndustryIdentifiers().get(0).getIdentifier());
            } catch (NumberFormatException | NullPointerException e) {
                continue;
            }
            Book book = Book.builder()
                    .isbn(isbn)
                    .name(getSubStr(volumeInfo.getTitle(), 31))
                    .price(volumeInfo.getPageCount())
                    .description(getSubStr(volumeInfo.getDescription(), 2047))
                    .imageUrl(volumeInfo.getImageLinks().getThumbnail())
                    .authors(authors)
                    .genres(genres)
                    .build();
            try {
                bookRepository.save(book);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getSubStr(String str, int length) {
        if (StringUtils.isBlank(str))
            return StringUtils.EMPTY;
        return str.length() > length ? str.substring(length) : str;
    }
}
