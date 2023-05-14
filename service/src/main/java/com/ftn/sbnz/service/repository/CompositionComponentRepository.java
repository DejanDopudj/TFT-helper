package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.service.model.CompositionComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompositionComponentRepository extends JpaRepository<CompositionComponent, Integer> {


}
