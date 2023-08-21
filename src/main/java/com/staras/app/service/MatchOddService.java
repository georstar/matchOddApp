package com.staras.app.service;

import com.staras.app.exceptions.EntityNotFoundException;
import com.staras.app.model.MatchOdd;
import com.staras.app.repository.MatchOddRepository;
import com.staras.app.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchOddService {

    @Autowired
    private MatchOddRepository matchOddRepository;
    @Autowired
    private MatchRepository matchRepository;

    public List<MatchOdd> getAll(){
        return matchOddRepository.findAll();
    }

    public MatchOdd findById(Long id){
        return matchOddRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Odd not found with id = " + id));
    }

    public List<MatchOdd> getOddsByMatchId(Long id){
        if (!matchRepository.existsById(id)){
            throw new EntityNotFoundException("Match not found with id = " + id);
        }
        return matchOddRepository.findByMatchId(id);
    }

    public MatchOdd create(Long matchId, MatchOdd matchOddRequest){
        MatchOdd matchOdd = matchRepository.findById(matchId).map(match -> {
            matchOddRequest.setMatch(match);
            return matchOddRepository.save(matchOddRequest);
        }).orElseThrow(() -> new EntityNotFoundException("Match not found with id = " + matchId));
        return matchOdd;
    }

    public MatchOdd update(Long id, MatchOdd matchOddRequest){
        MatchOdd matchOdd = findById(id);
        matchOdd.setOdd(matchOddRequest.getOdd());
        matchOdd.setSpecifier(matchOddRequest.getSpecifier());
        return matchOddRepository.save(matchOdd);
    }

    public void delete(Long id){
        matchOddRepository.deleteById(id);
    }

    public void deleteByMatchId(Long id){
        findById(id);
        matchOddRepository.deleteByMatchId(id);
    }
}
