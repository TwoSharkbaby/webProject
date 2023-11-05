package org.web.project.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j
@RequiredArgsConstructor
public class CommonController {
	
//	private final MemberService memberService;

	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {

		log.info("error : " + error);
		log.info("logout : " + logout);
		if (error != null) {
			model.addAttribute("error", "Login error check Your Account!");
		}

		if (logout != null) {
			model.addAttribute("logout", "Logout!!!");
		}

	}
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		log.info("custom logout");	
	}
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denied : " + auth);
		model.addAttribute("msg", "Access Denied");
	}
	
	@GetMapping("/customSingup")
	public void singup() {
	}
	
//	@PostMapping("/customSingup")
//	public String singup(MemberVO vo) {
//		log.info(vo);
//		return (memberService.singup(vo) == 1) ? "redirect:/board/list" : "/customLogin";
//	}

}
