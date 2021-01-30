package com.example.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @EqualsAndHashCode(of = "id")
public class Member {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String emailCheckToken;
	
	private LocalDateTime emailCheckTokenConfirm;
	
	private boolean tokenConfirm;
	
		
}
