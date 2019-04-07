package com.ia.dao;

import com.ia.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveDao extends JpaRepository<Leave, Integer> {
	@Query(nativeQuery = true, value = "select * from leaves where speciality = ?1 and " +
			"STR_TO_DATE(date1,'%Y-%m-%d') <= STR_TO_DATE(?2,'%Y-%m-%d') and STR_TO_DATE(date2,'%Y-%m-%d') >= STR_TO_DATE(?2,'%Y-%m-%d') and " +
			"cast(class1 as signed) <= cast(?3 as signed) and cast(class2 as signed) >= cast(?3 as signed) and teacher_name is not null ")
	List<Leave> findLeaver(String speciality, String date1, String class1);
}
