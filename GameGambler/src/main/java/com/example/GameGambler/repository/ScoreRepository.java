package com.example.GameGambler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.GameGambler.model.ScoreDetails;

@Repository
public interface ScoreRepository extends CrudRepository<ScoreDetails, Long>{

	@Query("SELECT s FROM ScoreDetails s where s.userid=?1")
	List<ScoreDetails> findByScoreDetailsOfUser(Integer userid);
}
