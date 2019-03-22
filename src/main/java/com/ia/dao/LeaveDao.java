package com.ia.dao;

import com.ia.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveDao extends JpaRepository<Leave, Integer> {
    @Query(nativeQuery = true, value = "select * from leaves where speciality = ?1 " +
            "and cast(date1 as integer) <= cast(?2 as integer) and cast(?2 as integer) <= cast(date2 as integer) " +
            "and class1 = ?3")
    List<Leave> findLeaver(String speciality, String date1, String class1);
}
