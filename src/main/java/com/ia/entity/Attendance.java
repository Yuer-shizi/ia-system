package com.ia.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String number;

	private String username;

	private String speciality;

	private String date1;

	private String class1;

	@OneToMany
	private List<User> Leaves;

	@OneToMany
	private List<User> truants;

}
