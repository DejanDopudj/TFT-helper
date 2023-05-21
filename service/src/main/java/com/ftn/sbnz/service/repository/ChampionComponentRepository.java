package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.Champion;
import com.ftn.sbnz.model.ChampionComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChampionComponentRepository extends JpaRepository<ChampionComponent, Integer> {

    List<ChampionComponent> findByChampionAndConnectionIsGreaterThan(Champion champion, Integer connection);
}
