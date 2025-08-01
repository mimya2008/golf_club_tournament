package com.keyin.rest.member;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    // Create a new member
    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return service.save(member);
    }

    // Get all members
    @GetMapping
    public List<Member> getAllMembers() {
        return service.findAll();
    }

    // Search by name, phone, or tournament startDate
    @GetMapping("/search")
    public List<Member> searchMembers(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String phone,
                                      @RequestParam(required = false)
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {

        if (name != null) {
            return service.searchByName(name);
        } else if (phone != null) {
            return service.searchByPhone(phone);
        } else if (startDate != null) {
            return service.findByTournamentStartDate(startDate);
        } else {
            return Collections.emptyList(); // avoids returning all if no filter is passed
        }
    }
}
