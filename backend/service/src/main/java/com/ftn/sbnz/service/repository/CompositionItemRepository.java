package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.CompositionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositionItemRepository extends JpaRepository<CompositionItem, Integer> {


}
