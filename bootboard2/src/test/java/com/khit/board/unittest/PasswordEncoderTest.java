package com.khit.board.unittest;

import com.khit.board.entity.Member;
import com.khit.board.entity.Role;
import com.khit.board.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder pwEncoder;

    @Test
    public void testInsertData(){
        //일반회원 - 저장
        Member member =  new Member();
        member.setMemberId("seolha86");
        member.setPassword(pwEncoder.encode("tjfgk9137!"));
        member.setName("김설하");
        member.setRole(Role.MEMBER);

        memberRepository.save(member);
    }

}
