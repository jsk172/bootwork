package com.khit.board.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Users {
    private Integer id;
    private String username;
    private String password;
    private String email;
}
