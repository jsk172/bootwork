package com.khit.board.controller;

import com.khit.board.entity.User;
import com.khit.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    //회원가입
    //포스트맨ㅇ json형태의 자료를 입력받아서 DB에 저장함
    @PostMapping("/user")
    public @ResponseBody String insertUser(@RequestBody User user){
        userService.save(user);
        return "회원가입 성공";
    }
    //회원목록보기
    @GetMapping("/user/list")
    public @ResponseBody List<User> getList(){
        List<User> userList = userService.findAll();
        return userList;
    }
    //회원 상세보기
    @GetMapping("/user/{id}")
    public @ResponseBody User getUser(@PathVariable Integer id){
        //검색된 회원이 없을 경우 예외 반환
        User findUser = userService.findById(id);
        return findUser;
    }
}
