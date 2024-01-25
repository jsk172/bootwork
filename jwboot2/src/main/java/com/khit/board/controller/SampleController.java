package com.khit.board.controller;

import com.khit.board.entity.Users;
import org.springframework.web.bind.annotation.*;

@RestController //데이터 전달이 역할인 어노테이션
public class SampleController {

    //GET - SELECT
    //객체를 반환하면 - json 형태로 전달됨
    @GetMapping("/khit")
    public Users httpGet(){
        //user 1명을 생성한 후 데이터 검색(보기)
        Users user = Users.builder()
                        .id(1)
                                .username("today")
                                        .password("1234")
                                                .email("today@naver.com")
                .build();
        return user;
    }
    //POST - INSERT
    //전달받은 데이터가 json 형태({key:value}) 일때 RequestBody를 사용
    @PostMapping("/khit")
    public String httpPost(@RequestBody Users users){
        return "POST 요청 처리" + users.toString();
    }
    //PUT - UPDATE
    @PutMapping("/khit")
    public String httpPut(@RequestBody Users users){
        return "PUT 요청 처리" + users.toString();
    }
    //DELETE - DELETE
    //@pathvariable은 경로 변수를 전달 받음
    @DeleteMapping("/khit/{id}")
    public String httpDelete(@PathVariable Integer id){
        return "DELETE 요청 처리" + id;
    }
}
