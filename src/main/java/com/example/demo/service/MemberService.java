package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Member;
import com.example.demo.domain.UserAccount;
import com.example.demo.form.SignUpForm;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JavaMailSender javaMailSender;

	public Member getSave(SignUpForm signUpForm) {
		
		Member member = new Member();
		member.setName(signUpForm.getName());
		member.setEmail(signUpForm.getEmail());
		member.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
		return memberRepository.save(member);
	}

	public void login(Member member) {
		UsernamePasswordAuthenticationToken token 
			= new UsernamePasswordAuthenticationToken(member.getEmail(), 
					member.getPassword(), 
					List.of(new SimpleGrantedAuthority("ROLE_USER")));
		SecurityContextHolder.getContext().setAuthentication(token);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Member member = memberRepository.findByEmail(email);
		if(member == null) {
			throw new UsernameNotFoundException("username is not Found");
		}
				
		return new UserAccount(member);
	}

	public void sendMail(Member member) {
//		Member member = memberRepository.findByEmail(signUpForm.getEmail());
		generationToken(member);
//		memberRepository.save(member);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(member.getEmail());
		mailMessage.setSubject("메일 인증 발송");
		mailMessage.setText("/check-email-token?token="+member.getEmailCheckToken()+"&email="+member.getEmail());
		System.out.println(mailMessage);
		javaMailSender.send(mailMessage);
	}
	
	
	public void generationToken(Member member) {
		member.setEmailCheckToken(UUID.randomUUID().toString());
		member.setEmailCheckTokenConfirm(LocalDateTime.now());
		member.setTokenConfirm(true);
	}
	
}
