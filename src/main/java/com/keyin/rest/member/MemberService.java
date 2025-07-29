package com.keyin.rest.member;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository repo;

    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    public Member save(Member member) {
        return repo.save(member);
    }

    public List<Member> findAll() {
        return repo.findAll();
    }

    public Optional<Member> findById(Long id) {
        return repo.findById(id);
    }

    public List<Member> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Member> searchByPhone(String phone) {
        return repo.findByPhone(phone);
    }
}

