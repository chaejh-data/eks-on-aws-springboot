package com.example.shop.controller;

import com.example.shop.dto.MemberDto;
import com.example.shop.service.MemberService;
import com.example.shop.exception.MemberNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(value="/member")
@RestController
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MemberService memberService;

    @ResponseBody
    @RequestMapping(value="/{memberId}", method=RequestMethod.GET)
    public ResponseEntity<?> findMember(@PathVariable Integer memberId) throws MemberNotFoundException {
        MemberDto memberDto = memberService.findMember(memberId);

        // 예외를 메시지 처리하는 방법?
        // 현재는 500 에러로 트레이스 됨.
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @ResponseBody
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ResponseEntity<List<?>> listMember() {
        List<MemberDto> memberDtolist = memberService.findAllMember();
        return ResponseEntity.status(HttpStatus.OK).body(memberDtolist);
    }


    @ResponseBody
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public ResponseEntity<?> addMember(@RequestBody MemberDto memberDto) {
        MemberDto addedMemberDto = memberService.add(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMemberDto);
    }

    @ResponseBody
    @RequestMapping(value="/{memberId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateMember(@PathVariable Integer memberId, @RequestBody MemberDto memberDto ) throws MemberNotFoundException {
        MemberDto updatedMemberDto = memberService.updateMember(memberId, memberDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMemberDto);
    }

}
