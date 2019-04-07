package com.ia.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
	@Id
	private String number;

	private String username;

	private String password;

	private String avatar;

	private String introduction;

	private String sex;

	private String age;

	// 0：学生 1：老师 -1：管理员
	private Integer type;

	// 学院
	private String department;

	// 专业
	private String speciality;

	// 邮件
	private String email;

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

}
