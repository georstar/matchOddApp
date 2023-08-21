package com.staras.app.controller;

import com.staras.app.model.MatchOdd;
import com.staras.app.service.MatchOddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/odds")
public class MatchOddController {

    @Autowired
    MatchOddService matchOddService;

    @GetMapping("/matches/{id}")
    public ResponseEntity<List<MatchOdd>> getOddsByMatchId(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(matchOddService.getOddsByMatchId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchOdd> getOddById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(matchOddService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MatchOdd>> getAll() {
        List<MatchOdd> matchOdds = matchOddService.getAll();
        return new ResponseEntity<>(matchOdds, HttpStatus.OK);
    }

    @PostMapping("/matches/{matchId}")
    public ResponseEntity<MatchOdd> createMatchOdd(@PathVariable(value = "matchId") Long matchId,
                                                   @RequestBody MatchOdd matchOddRequest) {
        return new ResponseEntity<>(matchOddService.create(matchId, matchOddRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchOdd> updateMatchOdd(@PathVariable("id") Long id, @RequestBody MatchOdd matchOddRequest) {
        return new ResponseEntity<>(matchOddService.update(id, matchOddRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMatchOdd(@PathVariable("id") Long id) {
        matchOddService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/matches/{id}")
    public ResponseEntity<List<MatchOdd>> deleteAllMatchOdds(@PathVariable(value = "id") Long id) {
        matchOddService.deleteByMatchId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
