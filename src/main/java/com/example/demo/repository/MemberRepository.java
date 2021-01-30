package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;

@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long>{

	boolean existsByEmail(String email);

	Member findByEmail(String email);

}
