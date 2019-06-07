package com.n2014758030.main.repository;

import com.n2014758030.main.domain.Basic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicRepository extends JpaRepository<Basic, Long> {
}
