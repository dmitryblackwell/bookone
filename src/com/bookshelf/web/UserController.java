package com.bookshelf.web;

import com.bookshelf.constant.PageConstants;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.Order;
import com.bookshelf.entity.User;
import com.bookshelf.service.BookService;
import com.bookshelf.service.FileUploadService;
import com.bookshelf.service.UserService;
import com.bookshelf.util.OrderNoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/{username}")
    public String getUser(@PathVariable String username, Model model){
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        model.addAttribute("orders", user.getOrders());
        return "userview";
    }

    @PostMapping("/{username}/orders")
    @ResponseBody
    public String saveOrder(@RequestParam("isbn") long isbn, @PathVariable("username") String username, @RequestParam("quantity") int quantity){
        userService.addOrderForUser(username, bookService.getBook(isbn), quantity);
        System.out.println("cool");
        return "order saved";
    }

    @DeleteMapping("/{username}/orders/{orderNo}")
    @ResponseBody
    public String deleteOrder(@PathVariable("username") String username, @PathVariable("orderNo") String orderNo){
        userService.deleteOrder(orderNo);
        return "order deleted";
    }


    private static final String IMG_PATH = "resources/uploaded-images/users";

    @PostMapping("/upload-image")
    @ResponseBody
    public String singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username, HttpSession session) {

        final String UPLOADED_PATH = session.getServletContext().getRealPath("/") + IMG_PATH + File.separator + String.valueOf(username) + ".jpg";
        fileUploadService.uploadFile(file, UPLOADED_PATH);

        return "users/"+username;
    }
}
