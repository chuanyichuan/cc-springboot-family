package com.example.image.extractor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = ImageExtractorApplication.class)
class ImageExtractorApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        System.out.println("sss");
        MyBean mb1 = (MyBean) context.getBean("myBean");
        System.out.println("message = " + mb1.getMessage());
        MyBean mb2 = (MyBean) context.getBean("&myBean");
        System.out.println("message = " + mb2.getMessage());
        System.out.println();
    }

}
