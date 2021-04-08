package cc.kevinlu.springcloudcuserservice.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable(name = "id") Integer id) {
        System.out.println("id = " + id);
        return "name = " + id;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestParam("message") String message, @RequestPart("attachment") MultipartFile file) {
        System.out.println("file = " + file);
        try (InputStream ins = file.getInputStream(); OutputStream os = new FileOutputStream("a.txt")) {
            IOUtils.copy(ins, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
