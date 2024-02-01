package com.khit.library.config;

import com.khit.library.entity.Member;
import com.khit.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<Member> findMember = memberRepository.findByMid(username);
        if(findMember.isEmpty()){
            throw new UsernameNotFoundException(username + "사용자 없음");
        }else{
            Member member = findMember.get();
            return new SecurityUser(member);
        }
    }
}
