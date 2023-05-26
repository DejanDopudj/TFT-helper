package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.Augment;
import com.ftn.sbnz.model.Composition;
import com.ftn.sbnz.model.CompositionAugment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CompositionAugmentRepository extends JpaRepository<CompositionAugment, Integer> {
    List<CompositionAugment> findByAugmentAndComposition(Augment augment, Composition composition);

}
