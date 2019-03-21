package com.ia.dao;

import com.ia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, String> {
	User findByNumberAndPassword(String number, String password);

	@Query(nativeQuery = true, value = "select distinct speciality from users")
	List<String> specialities();
}
