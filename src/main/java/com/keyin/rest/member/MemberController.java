package com.keyin.rest.member;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return service.save(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return service.findAll();
    }

    @GetMapping("/search")
    public List<Member> searchByName(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String phone) {
        if (name != null) return service.searchByName(name);
        if (phone != null) return service.searchByPhone(phone);
        return service.findAll();
    }
}

