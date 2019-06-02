package com.blackwell.web;

import com.blackwell.constant.PageConstants;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import com.blackwell.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BookService bookService;
    private final OrderService orderService;
    private final CommentService commentService;

    private final FileUploadService fileUploadService;


    @Autowired
    public UserController(UserService userService, BookService bookService, OrderService orderService,
                          CommentService commentService, FileUploadService fileUploadService) {
        this.userService = userService;
        this.bookService = bookService;
        this.orderService = orderService;
        this.commentService = commentService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/{username}")
    public String getUser(@PathVariable String username, Model model){
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getOrdersByUser(username));
        model.addAttribute("comments", commentService.getComments(username));
        return "userview";
    }

    @PostMapping("/{username}/orders")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void saveOrder(@RequestParam("isbn") long isbn, @PathVariable("username") String username) {
        Order order = Order.builder()
                .user(userService.getUser(username))
                .book(bookService.getBook(isbn))
                .build();
        orderService.saveOrder(order);
    }


    private static final String IMG_PATH = "resources/uploaded-images/users";

    @PostMapping("/upload-image")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username, HttpSession session) {
        String loginUsername = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        final String UPLOADED_PATH = session.getServletContext().getRealPath("/") + IMG_PATH + File.separator + String.valueOf(username) + ".jpg";

        if(username.equals(loginUsername))
            fileUploadService.uploadFile(file, UPLOADED_PATH);

        return PageConstants.REDIRECT_USERS + "/" + username;
    }
}
