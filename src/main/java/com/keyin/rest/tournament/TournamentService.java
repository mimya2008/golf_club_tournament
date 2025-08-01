package com.keyin.rest.tournament;

import com.keyin.rest.member.Member;
import com.keyin.rest.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final MemberRepository memberRepository;

    public TournamentService(TournamentRepository tournamentRepository, MemberRepository memberRepository) {
        this.tournamentRepository = tournamentRepository;
        this.memberRepository = memberRepository;
    }

    public Tournament save(Tournament tournament) {
        if (tournament.getMembers() == null) {
            tournament.setMembers(new HashSet<>());
        }
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    public List<Tournament> findByLocation(String location) {
        return tournamentRepository.findByLocation(location);
    }

    public List<Tournament> findByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public Tournament addMember(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        tournament.getMembers().add(member);
        member.getTournaments().add(tournament); // ✅ Important for bidirectional mapping

        memberRepository.save(member);           // ✅ Save both sides
        return tournamentRepository.save(tournament);
    }

}
