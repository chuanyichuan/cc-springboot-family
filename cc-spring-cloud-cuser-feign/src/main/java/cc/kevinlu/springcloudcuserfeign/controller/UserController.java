package cc.kevinlu.springcloudcuserfeign.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cc.kevinlu.springcloudcuserfeign.service.UserService;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/cuser/{id}")
    public String getUser(@PathVariable(name = "id") Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/cuser1/{id}")
    public String getUser1(@PathVariable(name = "id") Integer id) {
        File file = new File(
                "/Users/chuan/Documents/projects_code/git-project/springboot-family/cc-spring-cloud-cuser-feign/src/main/java/cc/kevinlu/springcloudcuserfeign/controller/UserController.java");
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file", MediaType.TEXT_PLAIN_VALUE,
                true, file.getName());

        try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        MultipartFile multi = new CommonsMultipartFile(fileItem);
        userService.upload("upload-message", multi);
        return "success";
    }

}
