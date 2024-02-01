package com.khit.board.dto;

import com.khit.board.entity.Member;
import com.khit.board.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class MemberDTO {
    private Integer id; //회원번호
    //아이디는 4자~20자로 입력
    @Size(min = 4, max = 20)
    @NotEmpty(message = "ID는 필수 항목입니다.")
    private String memberId; //아이디
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password; //비밀번호
    @NotEmpty(message = "이름은 필수 항목입니다.")
    private String name; //이름
    private Role role;

    private Timestamp createdDate;
    private Timestamp updatedDate;

    //entity(model<DB>에 저장됨) -> dto(view 보기) 변환
    //목록보기, 상세보기
    public static MemberDTO toSaveDTO(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .password(member.getPassword())
                .name(member.getName())
                .role(member.getRole())
                .createdDate(member.getCreatedDate())
                .updatedDate(member.getUpdatedDate())
                .build();
        return memberDTO;
    }
}
