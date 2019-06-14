package com.blackwell.web;

import com.blackwell.constant.PageConstants;
import com.blackwell.converter.BookToDTOConverter;
import com.blackwell.entity.Book;
import com.blackwell.entity.Comment;
import com.blackwell.entity.Genre;
import com.blackwell.model.BookFilter;
import com.blackwell.service.BookService;
import com.blackwell.service.CommentService;
import com.blackwell.service.FileUploadService;
import com.blackwell.util.GenreEditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
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
	public BookController(BookService bookService, CommentService commentService, GenreEditor genreEditor,
						  FileUploadService fileUploadService, BookToDTOConverter bookConverter) {
		this.bookService = bookService;
		this.commentService = commentService;
		this.genreEditor = genreEditor;
		this.fileUploadService = fileUploadService;
	}

	@GetMapping
	public ModelAndView getBooks(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("bestBooks", bookService.getBooksForSlider());
		modelAndView.addObject("searchValue", getSearchValue(session));
		modelAndView.addObject("loadBooks", true);
		modelAndView.addObject("genres", bookService.getGenres());
		return modelAndView;
	}

	@GetMapping("/ajax/load")
	public ModelAndView loadBooksByPage(HttpSession session, Integer pageNo) {
		session.setAttribute("pageNo", pageNo);
		return getBooksTable(getFilterFromSession(session));
	}

	@GetMapping("/ajax/sort")
	public ModelAndView sortBooks(HttpSession session, String sortColumn) {
		session.setAttribute("sortColumn", sortColumn);
		return getBooksTable(getFilterFromSession(session));
	}

	@GetMapping("/ajax/search")
	public ModelAndView searchBooks(HttpSession session, String searchValue) {
		session.setAttribute("searchValue", searchValue);
		return getBooksTable(getFilterFromSession(session));
	}

	@GetMapping("/ajax/genre")
	public ModelAndView loadBooksByGenresNames(HttpSession session, @RequestParam(value="genresNames[]") @Nullable List<String> genresNames) {
		session.setAttribute("genresNames", genresNames);
		return getBooksTable(getFilterFromSession(session));
	}

	private BookFilter getFilterFromSession(HttpSession session) {
		return new BookFilter((Integer) session.getAttribute("pageNo"),
				String.valueOf(session.getAttribute("sortColumn")),
				(String) session.getAttribute("searchValue"),
				(List<String>) session.getAttribute("genresNames"));
	}

	@PostMapping("/{isbn}")
	public ModelAndView saveBook(@ModelAttribute Book book) {
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
	public ModelAndView saveComment(@PathVariable long isbn, Comment comment){
		commentService.saveComment(comment);

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
    	final String UPLOADED_PATH = session.getServletContext().getRealPath("/") + IMG_PATH + File.separator + isbn + ".jpg";
		fileUploadService.uploadFile(file, UPLOADED_PATH);

        return PageConstants.REDIRECT_BOOKS + "/" + isbn + "?";
    }

    private ModelAndView getBookEditPage(long isbn) {
		ModelAndView modelAndView = new ModelAndView("book-edit");
		Book book = (isbn != 0) ? bookService.getBook(isbn) : new Book();
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

	private ModelAndView getBooksTable(BookFilter filter) {
		ModelAndView modelAndView = new ModelAndView("snippets/book-table");
		modelAndView.addObject("books", bookService.getBooks(filter));
		modelAndView.addObject("currentPage", filter.getPageNo());
		modelAndView.addObject("pagesCount", bookService.getPagesCount());
		modelAndView.addObject("sortColumn", filter.getSortColumn());
		return modelAndView;
	}


	private String getSearchValue(HttpSession session) {
		String searchValue = (String) session.getAttribute("searchValue");
		return StringUtils.isBlank(searchValue) ? StringUtils.EMPTY : searchValue;
	}

}
