package com.ia.dao;

import com.ia.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveDao extends JpaRepository<Leave, Integer> {
}
