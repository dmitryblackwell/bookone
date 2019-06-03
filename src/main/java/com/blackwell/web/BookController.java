package com.blackwell.web;

import com.blackwell.constant.PageConstants;
import com.blackwell.entity.Book;
import com.blackwell.entity.Comment;
import com.blackwell.entity.Genre;
import com.blackwell.service.BookService;
import com.blackwell.service.CommentService;
import com.blackwell.service.FileUploadService;
import com.blackwell.util.GenreEditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;
	private final CommentService commentService;
	private final GenreEditor genreEditor;
	private final FileUploadService fileUploadService;

	@Autowired
	public BookController(BookService bookService, CommentService commentService, GenreEditor genreEditor, FileUploadService fileUploadService) {
		this.bookService = bookService;
		this.commentService = commentService;
		this.genreEditor = genreEditor;
		this.fileUploadService = fileUploadService;
	}

	@GetMapping
	public ModelAndView getBooks() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("books", bookService.getBooks());
		modelAndView.addObject("book", new Book());
		return modelAndView;
	}
	

	@PostMapping("/{isbn}")
	public ModelAndView saveBook(@PathVariable long isbn, @ModelAttribute Book book) {
		bookService.saveBook(book);
		return new ModelAndView(PageConstants.REDIRECT_HOME);
	}

	@DeleteMapping("/{isbn}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable long isbn) {
		bookService.deleteBook(isbn);
	}

	@GetMapping("/{isbn}")
	public ModelAndView getBook(@PathVariable long isbn, @RequestParam(required = false) boolean edit) {
		return edit ? getBookEditPage(isbn) : getBookPage(isbn);
	}

	@PostMapping("/{isbn}/comments")
	public ModelAndView saveComment(@PathVariable long isbn, @RequestParam String username, @RequestParam String comment){
		commentService.saveComment(isbn, username, comment);

		String viewName = StringUtils.join(PageConstants.REDIRECT_BOOKS, "/", isbn);
		return new ModelAndView(viewName);
	}

	@DeleteMapping("/comments/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteComment(@PathVariable int id){
		commentService.deleteComment(id);
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

    private ModelAndView getBookEditPage(long isbn) {
		ModelAndView modelAndView = new ModelAndView("book-edit");
		Book book;
		if (isbn != 0)
			book = bookService.getBook(isbn);
		else
			book = new Book();
		modelAndView.addObject("genres", bookService.getGenres());
		modelAndView.addObject("book", book);
		return modelAndView;
	}

	private ModelAndView getBookPage(long isbn) {
		Book book = bookService.getBook(isbn);
		List<Comment> comments = commentService.getComments(isbn);
		ModelAndView modelAndView = new ModelAndView("bookview");
		modelAndView.addObject("book", book);
		modelAndView.addObject("comments", comments);
		return modelAndView;
	}
	
}
