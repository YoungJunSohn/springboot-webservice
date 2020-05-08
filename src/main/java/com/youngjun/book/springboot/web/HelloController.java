package com.youngjun.book.springboot.web;

import com.youngjun.book.springboot.web.dto.HelloResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/dto")
    public HelloResponseDTO helloDTO(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        return new HelloResponseDTO(name, amount);
    }//HelloDTO()

    @GetMapping("/hello")
    public String hello(){
      return  "hello";
    }//hello()
}//HelloController
