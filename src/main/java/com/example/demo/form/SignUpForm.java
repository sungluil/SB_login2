package com.example.demo.form;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank
	@Length(min = 3, max = 10)
	private String name;
	
	@NotBlank
	@Column(unique = true)
	@Email
	private String email;
	
	@NotBlank
	private String password;
}
