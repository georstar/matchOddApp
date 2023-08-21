package com.staras.app.repository;

import com.staras.app.model.MatchOdd;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchOddRepository extends JpaRepository<MatchOdd,Long> {
    List<MatchOdd> findByMatchId(Long matchId);

    @Transactional
    void deleteByMatchId(long matchId);
}
