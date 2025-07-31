package com.keyin.rest.tournament;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TournamentControllerTest {

    private final TournamentService tournamentService = Mockito.mock(TournamentService.class);
    private final TournamentController tournamentController = new TournamentController(tournamentService);

    @Test
    void testGetAllTournaments() {
        List<Tournament> tournaments = List.of(
                createTournament("Spring Classic", LocalDate.parse("2025-05-01")),
                createTournament("Summer Cup", LocalDate.parse("2025-07-10"))
        );

        Mockito.when(tournamentService.findAll()).thenReturn(tournaments);

        List<Tournament> result = tournamentController.all();

        assertEquals(2, result.size());
        assertEquals("Spring Classic", result.get(0).getName());
    }

    @Test
    void testSearchByLocation() {
        List<Tournament> tournaments = List.of(
                createTournament("Local Tourney", LocalDate.parse("2025-08-01"))
        );

        Mockito.when(tournamentService.findByLocation("St. John's")).thenReturn(tournaments);

        List<Tournament> result = tournamentController.search("St. John's", null);

        assertEquals(1, result.size());
        assertEquals("Local Tourney", result.get(0).getName());
    }

    @Test
    void testSearchByStartDate() {
        LocalDate date = LocalDate.parse("2025-08-01");
        List<Tournament> tournaments = List.of(
                createTournament("Start Date Match", date)
        );

        Mockito.when(tournamentService.findByStartDate(date)).thenReturn(tournaments);

        List<Tournament> result = tournamentController.search(null, "2025-08-01");

        assertEquals(1, result.size());
        assertEquals("Start Date Match", result.get(0).getName());
    }

    // Helper method to create a Tournament instance with name and startDate
    private Tournament createTournament(String name, LocalDate startDate) {
        Tournament t = new Tournament();
        t.setName(name);
        t.setStartDate(startDate);
        return t;
    }
}
