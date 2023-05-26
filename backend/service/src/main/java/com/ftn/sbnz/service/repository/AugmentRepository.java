package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.Augment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AugmentRepository extends JpaRepository<Augment, String> {


}
