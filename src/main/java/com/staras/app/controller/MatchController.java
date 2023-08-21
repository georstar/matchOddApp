package com.staras.app.controller;

import com.staras.app.model.Match;
import com.staras.app.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    @Autowired
    MatchService matchService;

    @GetMapping
    public ResponseEntity<List<Match>> getAll() {
        List<Match> matches = matchService.getAll();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") Long id) {
        Match match = matchService.findById(id);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Match> create(@RequestBody Match match) {
        Match newMatch = matchService.create(match);
        return new ResponseEntity<>(newMatch, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable("id") Long id, @RequestBody Match match) {
        return new ResponseEntity<>(matchService.update(id, match), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMatch(@PathVariable("id") Long id) {
        matchService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllMatches() {
        matchService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
