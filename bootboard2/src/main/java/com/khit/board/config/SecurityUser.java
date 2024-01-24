package com.khit.board.config;


import com.khit.board.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUser extends User {

    private Member member;
    public SecurityUser(Member member) {
        //암호화 안된 상태는 "{noop}" 사용함
        super(member.getMemberId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));

        this.member = member;
    }
    public Member getMember(){
        return member;
    }
}
