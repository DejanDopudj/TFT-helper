package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, String> {


}
