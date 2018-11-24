package com.bookshelf.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import com.bookshelf.entity.Comment;
import com.bookshelf.entity.User;
import com.bookshelf.service.FileUploadService;
import com.bookshelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookshelf.constant.PageConstants;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.Genre;
import com.bookshelf.service.BookService;
import com.bookshelf.util.GenreEditor;

@Controller
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private GenreEditor genreEditor;

	@Autowired
	private UserService userService;

	@Autowired
	private FileUploadService fileUploadService;

	// get all books
	@GetMapping
	public String getBooks(Model model) {
		model.addAttribute("books", bookService.getBooks());
		model.addAttribute("genres", bookService.getGenres());
		model.addAttribute("book", new Book());
		return "index";
	}
	
	
	// save or update one book
	@PostMapping
	public String saveBook(@ModelAttribute Book book) {
		bookService.saveBook(book);
		System.out.println(book);
		return PageConstants.REDIRECT_HOME;
	}

	// delete book
	@DeleteMapping("/{isbn}")
	@ResponseBody
	public String delete(@PathVariable long isbn) {
		bookService.deleteBook(isbn);
		return "book savely deleted";
	}
	
	
	// get one book
	@GetMapping("/{isbn}")
	public String getBook(@PathVariable long isbn, Model model) {		
		Book book = bookService.getBook(isbn);
		model.addAttribute("book", book);		
		
		return "bookview";
	}

	@PostMapping("/{isbn}/comments")
	public String saveComment(@PathVariable long isbn, @RequestParam String username, @RequestParam String comment){
		bookService.addComment(isbn, username, comment);

		return PageConstants.REDIRECT_BOOKS +"/" + isbn;
	}

	@DeleteMapping("/{isbn}/comments/{id}")
	@ResponseBody
	public String deleteComment(@PathVariable long isbn, @PathVariable int id){
		bookService.deleteComment(id);
		return "comment deleted";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Genre.class, this.genreEditor);
	}
	
	
	private static final String IMG_PATH = "resources/uploaded-images/books";
	
	@PostMapping("/upload-image") 
    public String singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("isbn") long isbn, HttpSession session) {
		
    	final String UPLOADED_PATH = session.getServletContext().getRealPath("/") + IMG_PATH + File.separator + String.valueOf(isbn) + ".jpg";
		fileUploadService.uploadFile(file, UPLOADED_PATH);

        return PageConstants.REDIRECT_BOOKS + "/" + isbn + "?";
    }
	
}
