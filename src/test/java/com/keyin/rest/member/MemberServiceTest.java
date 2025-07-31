package com.keyin.rest.member;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class MemberServiceTest {
    private final MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
    private final MemberService memberService = new MemberService(memberRepository);

    @Test
    void testGetAllMembers() {
        List<Member> members = Arrays.asList(
                new Member() {{ setName("John Doe"); }},
                new Member() {{ setName("Jane Doe"); }}
        );

        Mockito.when(memberRepository.findAll()).thenReturn(members);

        List<Member> result = memberService.getAllMembers();
        assertEquals(2, result.size());
    }

    @Test
    void testGetMemberById() {
        Member member = new Member();
        member.setId(1L);
        member.setName("John Smith");

        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        Member result = memberService.getMemberById(1L);
        assertEquals("John Smith", result.getName());
    }
}
