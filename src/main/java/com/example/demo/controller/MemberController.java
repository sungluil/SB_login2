package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Member;
import com.example.demo.form.SignUpForm;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	private final MemberRepository memberRepository; 

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/sign-up")
	public String signUp(Model model) {
		model.addAttribute(new SignUpForm());
		return "sign-up";
	}
	
	@PostMapping("/sign-up")
	public String signUpForm(@Valid SignUpForm signUpForm, Errors errors, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("error", "에러가 발생했습니다.");
			return "/sign-up";
		}
		
		Member member = memberRepository.findByEmail(signUpForm.getEmail());
		if(member != null) {
			model.addAttribute("error", "존재하는 아이디입니다..");
			return "/sign-up";
		}
//		log.info(member.getName());
		Member account = memberService.getSave(signUpForm);	
		memberService.login(account);
		memberService.sendMail(account);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
}




