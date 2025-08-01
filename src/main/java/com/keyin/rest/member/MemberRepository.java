package com.keyin.rest.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByNameContainingIgnoreCase(String name);

    List<Member> findByPhoneContaining(String phone);

    // Query to find members by tournament start date
    @Query("SELECT m FROM Member m JOIN m.tournaments t WHERE t.startDate = :date")
    List<Member> findByTournamentStartDate(@Param("date") LocalDate date);
}

