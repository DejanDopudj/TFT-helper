package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComponentRepository extends JpaRepository<Component, String> {


}
