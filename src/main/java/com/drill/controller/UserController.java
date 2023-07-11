package com.drill.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drill.domain.User;
import com.drill.dto.ResUserDto;
import com.drill.svc.UserService;

@Controller
public class UserController {
	

	@Autowired
	private UserService userService;
	
	// 회원가입
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}


	@PostMapping("/join")
	public @ResponseBody ResUserDto<?> postJoin(@RequestBody User user) {
		
		
		User findUser = userService.getUser(user.getUserid());
			
		if (findUser.getUserid() == null) {
			userService.join(user);
			return new ResUserDto<>(HttpStatus.OK.value(), user.getUserid() + " 회원가입 완료!");
		} else {
			return new ResUserDto<>(HttpStatus.BAD_REQUEST.value(), user.getUserid() + " 는 사용중입니다.");
		}
		
	}
	

	
	// 로그인
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	


	@PostMapping("/login")
	public @ResponseBody ResUserDto<?> postLogin(@RequestBody User user, HttpSession session) {
	
		User findUser = userService.getUser(user.getUserid());
				
		if (findUser.getUserid() == null) {
			// 아이디 없음
			return new ResUserDto<>(HttpStatus.BAD_REQUEST.value(), "로그인 정보를 확인하세요.");
		} else {
			if (user.getPassword().equals(findUser.getPassword())) {
				// 로그인 정상 처리
				session.setAttribute("sid", findUser);
				session.setMaxInactiveInterval(300);
				return new ResUserDto<>(HttpStatus.OK.value(), findUser.getUserid() + " 로그인 중!");
			} else {
				// 비밀번호 틀림
				return new ResUserDto<>(HttpStatus.BAD_REQUEST.value(), "로그인 정보를 확인하세요.");
			}
				
		}
			
	}
	


	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
}
