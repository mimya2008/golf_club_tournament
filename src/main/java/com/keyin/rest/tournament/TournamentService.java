package com.keyin.rest.tournament;

import com.keyin.rest.member.Member;
import com.keyin.rest.member.MemberRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository repo;
    private final MemberRepository memberRepo;

    public TournamentService(TournamentRepository repo, MemberRepository memberRepo) {
        this.repo = repo;
        this.memberRepo = memberRepo;
    }

    public Tournament save(Tournament tournament) {
        return repo.save(tournament);
    }

    public List<Tournament> findAll() {
        return repo.findAll();
    }

    public List<Tournament> findByLocation(String location) {
        return repo.findByLocationContainingIgnoreCase(location);
    }

    public List<Tournament> findByStartDate(LocalDate date) {
        return repo.findByStartDate(date);
    }

    public Tournament addMember(Long tournamentId, Long memberId) {
        Tournament tournament = repo.findById(tournamentId).orElseThrow();
        Member member = memberRepo.findById(memberId).orElseThrow();
        tournament.getMembers().add(member);
        return repo.save(tournament);
    }
}

