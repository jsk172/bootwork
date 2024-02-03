package com.khit.library.controller;

import com.khit.library.config.SecurityUser;
import com.khit.library.dto.MemberDTO;
import com.khit.library.dto.RentalReturnDTO;
import com.khit.library.entity.Member;
import com.khit.library.service.MemberService;
import com.khit.library.service.RentalReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rentalReturn")
public class RentalReturnController {
    private final RentalReturnService rentalReturnService;
    private final MemberService memberService;
    //도서대출
    @RequestMapping(value = "/rental", method = RequestMethod.POST)
    public ResponseEntity<?> rental(@RequestBody RentalReturnDTO rentalReturnDTO, @AuthenticationPrincipal SecurityUser user){
        rentalReturnDTO.setMember(user.getMember());
        rentalReturnService.save(rentalReturnDTO);
        System.out.println(rentalReturnDTO);
        return ResponseEntity.ok(rentalReturnDTO);
    }
    /*@PostMapping("/rental") @ResponseBody
    public String rental(@RequestBody RentalReturnDTO rentalReturnDTO){
        System.out.println(rentalReturnDTO);
        rentalReturnService.save(rentalReturnDTO);
        return "redirect:/rental/list";
    }*/

    //대출리스트
    @GetMapping("/list")
    public String getList(Model model){
        List<RentalReturnDTO> rentalReturnDTOList = rentalReturnService.findAll();
        model.addAttribute("rentalList", rentalReturnDTOList);
        return "rental/list";
    }
}
