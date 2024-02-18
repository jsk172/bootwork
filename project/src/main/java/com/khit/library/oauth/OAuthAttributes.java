/*
package com.khit.library.oauth;

import com.khit.library.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String email;
    private Role role;

    public String OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes){
        if("naver".equals(registrationId)){
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String ,Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        log.info("naver response : " + response);

        return com.khit.library.oauth.OAuthAttributes.builder()
                .username((String) response.get("email"))
                .email((String) response.get("email"))
                .attributes(response)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .username(username)
                .email(email)
                .role(Role.Member)
                .build();
    }
}
*/
