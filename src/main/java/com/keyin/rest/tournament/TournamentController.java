package com.keyin.rest.tournament;

import com.keyin.rest.member.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService service;
    private final TournamentRepository tournamentRepository;

    public TournamentController(TournamentService service, TournamentRepository tournamentRepository) {
        this.service = service;
        this.tournamentRepository = tournamentRepository;
    }

    // Create a new tournament
    @PostMapping
    public Tournament create(@RequestBody Tournament tournament) {
        return service.save(tournament);
    }

    // Get all tournaments
    @GetMapping
    public List<Tournament> all() {
        return service.findAll();
    }

    // Search by location or start date
    @GetMapping("/search")
    public List<Tournament> search(@RequestParam(required = false) String location,
                                   @RequestParam(required = false) String startDate) {
        if (location != null) return service.findByLocation(location);
        if (startDate != null) return service.findByStartDate(LocalDate.parse(startDate));
        return service.findAll();
    }

    // Add a member to a tournament
    @PostMapping("/{tournamentId}/members/{memberId}")
    public Tournament addMember(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        return service.addMember(tournamentId, memberId);
    }

    // Get all members in a tournament
    @GetMapping("/{id}/members")
    public ResponseEntity<Set<Member>> getMembersByTournament(@PathVariable Long id) {
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        return tournament.map(value -> ResponseEntity.ok(value.getMembers()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
