package com.blackwell.web;

import com.blackwell.constant.PageConstants;
import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.service.BookService;
import com.blackwell.service.FileUploadService;
import com.blackwell.util.GenreEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping("/books")
public class BooksController {
	private final BookService bookService;
	
	private final GenreEditor genreEditor;

	private final FileUploadService fileUploadService;

	@Autowired
	public BooksController(BookService bookService, GenreEditor genreEditor, FileUploadService fileUploadService) {
		this.bookService = bookService;
		this.genreEditor = genreEditor;
		this.fileUploadService = fileUploadService;
	}

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
		return "book safely deleted";
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

	@DeleteMapping("/comments/{id}")
	@ResponseBody
	public String deleteComment(@PathVariable int id){
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
