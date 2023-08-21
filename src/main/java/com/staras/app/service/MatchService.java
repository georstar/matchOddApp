package com.staras.app.service;

import com.staras.app.exceptions.EntityNotFoundException;
import com.staras.app.model.Match;
import com.staras.app.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> getAll() {
        return matchRepository.findAll();
    }

    public Match findById(Long id){
        return matchRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Match not found with id = " + id));
    }

    public Match create(Match match){
        return matchRepository.save(new Match(match.getDescription(), match.getMatchDate(), match.getMatchTime(), match.getTeamA(), match.getTeamB(), match.getSport()));
    }

    public Match update(Long id, Match match){
        Match matchToUpdate = findById(id);
        matchToUpdate.setMatchDate(match.getMatchDate());
        matchToUpdate.setMatchTime(match.getMatchTime());
        matchToUpdate.setDescription(match.getDescription());
        matchToUpdate.setSport(match.getSport());
        matchToUpdate.setTeamA(match.getTeamA());
        matchToUpdate.setTeamB(match.getTeamA());
        return matchRepository.save(matchToUpdate);
    }

    public void delete(Long id){
        matchRepository.deleteById(id);
    }

    public void deleteAll(){
        matchRepository.deleteAll();
    }
}
