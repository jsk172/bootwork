package com.khit.board.repository;

import com.khit.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    //save(), findAll(), findById(), deleteById()
}
