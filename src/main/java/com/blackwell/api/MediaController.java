package com.blackwell.api;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/api/media")
@AllArgsConstructor
public class MediaController {

    private ServletContext servletContext;

    @GetMapping("/book/{isbn}")
    public void getImageAsByteArray(HttpServletResponse response, @PathVariable long isbn) throws IOException {
        InputStream in = servletContext.getResourceAsStream("resources/uploaded-images/books/" + isbn + ".jpg");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
