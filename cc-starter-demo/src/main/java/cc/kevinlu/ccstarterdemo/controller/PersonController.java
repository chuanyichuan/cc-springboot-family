package cc.kevinlu.ccstarterdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.ccstarterdemo.PersonDTO;

@RestController
public class PersonController {

    //    @Autowired
    //    private CcService ccService;

    //    @GetMapping("/info")
    //    public void info() {
    //        System.out.println(ccService.info());
    //    }

    @GetMapping("/info")
    public PersonDTO person() {
        PersonDTO d = new PersonDTO();
        d.setId(12L);
        d.setName("cc");
        d.setAge(18);
        d.setGender("male");
        d.setS(123L);
        return d;
    }

}
