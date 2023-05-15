package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.CompositionAugment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositionAugmentRepository extends JpaRepository<CompositionAugment, Integer> {


}
