package com.ia.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "leaves")
public class Leave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	private String teacherName;

	private String speciality;

	private String date1;

	private String class1;

	private String date2;

	private String class2;

	private String reason;


}
