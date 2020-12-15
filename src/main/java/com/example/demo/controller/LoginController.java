package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.demo.dao.ClassService;
import com.example.demo.dao.UserService;
import com.example.demo.dto.User;
import com.example.demo.dto.Class;
import com.example.demo.model.UserBean;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private ClassService classService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("user", new UserBean());
		return "LGN001";
	}
	
	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String header() {
		return "M00001";
	}
	
	@RequestMapping(value = "/LoginServlet", method = RequestMethod.POST)
	public String login(HttpServletRequest request,@ModelAttribute("user")UserBean bean,Model model) {
		if (bean.getId().equals("") || bean.getPassword().equals("")) {
			request.setAttribute("err", "Feilds must not be null");
			model.addAttribute("user", bean);
			return "LGN001";
		} else {
			User dto = new User();
			dto.setId(bean.getId());
			
			Optional<User> list = userService.getUserById(bean.getId());
			if (!list.isPresent()) {
				request.setAttribute("err", "User not found!");
				model.addAttribute("user", bean);
				return "LGN001";
			}
			
			User user=list.get();
			 if (bean.getPassword().equals(user.getPassword())) {		
				Class cdto = new Class();
				cdto.setId("");
				cdto.setName("");
				request.getServletContext().setAttribute("classlist", classService.getAllClasses());
				request.getSession().setAttribute("sesUser",user);
				return "M00001";
			} else {
				request.setAttribute("err", "Password is incorrect!");
				return "LGN001";
			}
		}
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model) {
		model.addAttribute("user", new UserBean());
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "LGN001";
	}
	
}
