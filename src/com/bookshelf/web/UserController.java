package com.bookshelf.web;

import com.bookshelf.constant.PageConstants;
import com.bookshelf.entity.User;
import com.bookshelf.service.FileUploadService;
import com.bookshelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/{username}")
    public String getUser(@PathVariable String username, Model model){
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        return "userview";
    }

    private static final String IMG_PATH = "resources/uploaded-images/users";

    @PostMapping("/upload-image")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username, HttpSession session) {

        final String UPLOADED_PATH = session.getServletContext().getRealPath("/") + IMG_PATH + File.separator + String.valueOf(username) + ".jpg";
        fileUploadService.uploadFile(file, UPLOADED_PATH);

        return PageConstants.REDIRECT_USERS + "/" + username + "?";
    }
}
