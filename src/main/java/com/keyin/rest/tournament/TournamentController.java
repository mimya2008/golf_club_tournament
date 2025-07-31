package com.keyin.rest.tournament;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService service;

    public TournamentController(TournamentService service) {
        this.service = service;
    }

    @PostMapping
    public Tournament create(@RequestBody Tournament tournament) {
        return service.save(tournament);
    }

    @GetMapping
    public List<Tournament> all() {
        return service.findAll();
    }

    @GetMapping("/search")
    public List<Tournament> search(@RequestParam(required = false) String location,
                                   @RequestParam(required = false) String startDate) {
        if (location != null) return service.findByLocation(location);
        if (startDate != null) return service.findByStartDate(LocalDate.parse(startDate));
        return service.findAll();
    }

    @PostMapping("/{tournamentId}/members/{memberId}")
    public Tournament addMember(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        return service.addMember(tournamentId, memberId);
    }
}
