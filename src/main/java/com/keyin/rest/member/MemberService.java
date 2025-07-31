package com.keyin.rest.member;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }


    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public List<Member> searchByName(String name) {
        // Assuming you have a method in your repository to search by name
        return memberRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Member> searchByPhone(String phone) {
        // Assuming you have a method in your repository to search by phone
        return memberRepository.findByPhoneContaining(phone);
    }
}
