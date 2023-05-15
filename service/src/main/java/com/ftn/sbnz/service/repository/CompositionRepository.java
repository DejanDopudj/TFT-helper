package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.Composition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, String> {


}
