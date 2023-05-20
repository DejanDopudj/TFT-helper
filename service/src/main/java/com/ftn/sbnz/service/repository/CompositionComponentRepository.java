package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.Champion;
import com.ftn.sbnz.model.CompositionComponent;
import com.ftn.sbnz.model.ChampionComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompositionComponentRepository extends JpaRepository<CompositionComponent, Integer> {


}
