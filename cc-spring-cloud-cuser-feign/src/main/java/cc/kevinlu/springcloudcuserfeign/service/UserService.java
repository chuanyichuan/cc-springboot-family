package cc.kevinlu.springcloudcuserfeign.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import feign.form.spring.converter.SpringManyMultipartFilesReader;

@FeignClient(name = "cuser-service", configuration = UserService.MultipartSupportConfig.class)
public interface UserService {

    @GetMapping("/user/{id}")
    String getUser(@PathVariable(name = "id") Integer id);

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void upload(@RequestParam("message") String message, @RequestPart("attachment") MultipartFile file);

    class MultipartSupportConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }

    class ClientConfiguration {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Decoder feignDecoder() {
            List<HttpMessageConverter<?>> springConverters = messageConverters.getObject().getConverters();

            List<HttpMessageConverter<?>> decoderConverters = new ArrayList<HttpMessageConverter<?>>(
                    springConverters.size() + 1);

            decoderConverters.addAll(springConverters);
            decoderConverters.add(new SpringManyMultipartFilesReader(4096));

            HttpMessageConverters httpMessageConverters = new HttpMessageConverters(decoderConverters);

            return new SpringDecoder(() -> httpMessageConverters);
        }
    }

}
