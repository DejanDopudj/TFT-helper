package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.CompositionComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositionComponentRepository extends JpaRepository<CompositionComponent, Integer> {


}
