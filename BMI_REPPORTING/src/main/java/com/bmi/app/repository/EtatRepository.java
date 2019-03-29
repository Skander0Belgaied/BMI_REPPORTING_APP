package com.bmi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmi.app.entity.Etat;

@Repository
public interface EtatRepository extends JpaRepository<Etat, Long>{

}
