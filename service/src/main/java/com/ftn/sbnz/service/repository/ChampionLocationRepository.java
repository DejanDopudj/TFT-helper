package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.AugmentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionLocationRepository extends JpaRepository<AugmentLocation, Integer> {


}
