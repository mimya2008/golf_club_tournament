package com.keyin.rest.tournament;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.keyin.rest.member.Member;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_name")
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private Double entryFee;
    private Double cashPrize;

    @ManyToMany
    @JoinTable(
            name = "tournament_member",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> members = new HashSet<>();

    // No-arg constructor for JPA
    public Tournament() {}

    // Existing constructor for simple cases
    public Tournament(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    // New full constructor for tests and other use cases
    public Tournament(String name, LocalDate startDate, LocalDate endDate,
                      String location, Double entryFee, Double cashPrize) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrize = cashPrize;
    }

    // Getters and setters below

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(Double entryFee) {
        this.entryFee = entryFee;
    }

    public Double getCashPrize() {
        return cashPrize;
    }

    public void setCashPrize(Double cashPrize) {
        this.cashPrize = cashPrize;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
