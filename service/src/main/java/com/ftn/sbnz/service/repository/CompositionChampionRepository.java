package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.service.model.CompositionChampion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositionChampionRepository extends JpaRepository<CompositionChampion, Integer> {


}
