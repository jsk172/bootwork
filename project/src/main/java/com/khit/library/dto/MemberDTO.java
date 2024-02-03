package com.khit.library.dto;

import com.khit.library.entity.Member;
import com.khit.library.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class MemberDTO {
    private Long memberId;

    private String mid;

    private String password;

    private String email;

    private String name;

    private Integer rentalCount;

    private Boolean rentalAble;

    private Role role;

    private Timestamp createdDate;
    private Timestamp updatedDate;


    public static MemberDTO toSaveDTO(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(member.getMemberId())
                .mid(member.getMid())
                .password(member.getPassword())
                .email(member.getEmail())
                .name(member.getName())
                .rentalCount(member.getRentalCount())
                .rentalAble(member.getRentalAble())
                .role(member.getRole())
                .createdDate(member.getCreatedDate())
                .updatedDate(member.getUpdatedDate())
                .build();
        return memberDTO;
    }
}
