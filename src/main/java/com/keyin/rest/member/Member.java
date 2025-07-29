package com.keyin.rest.member;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import com.keyin.rest.tournament.Tournament;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phone;
    private LocalDate startDate;
    private int durationInMonths;

    @ManyToMany(mappedBy = "members")
    private Set<Tournament> tournaments;
}
