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
        Optional<Tournament> tournamentOpt = tournamentRepository.findById(tournamentId);
        Optional<Member> memberOpt = memberRepository.findById(memberId);

        if (tournamentOpt.isPresent() && memberOpt.isPresent()) {
            Tournament tournament = tournamentOpt.get();
            Member member = memberOpt.get();
            tournament.getMembers().add(member);
            return tournamentRepository.save(tournament);
        }
        throw new RuntimeException("Tournament or Member not found");
    }
}
