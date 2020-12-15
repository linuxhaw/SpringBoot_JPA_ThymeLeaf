package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UserService;
import com.example.demo.dto.User;
import com.example.demo.model.UserBean;

@Controller
public class UserController {
	@Autowired
	 private UserService userService;
	
	@ModelAttribute("userBean")
	public UserBean getUserBean() {
		return new UserBean();
	}
	
	@RequestMapping(value = "/userManagement", method = RequestMethod.GET)
	public String userManagement(Model model) {
		return "USR001";
	}
	
	@RequestMapping(value = "/userSearch", method = RequestMethod.POST)
	public String userSearch(@ModelAttribute("userBean")UserBean bean,Model model) {
		User dto=new User();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		
		if (!dto.getId().equals("")) {
			List<User> list = userService.getAllUsersbyId(bean.getId());
			System.out.print(list.size());
			model.addAttribute("userlist", list);
			return "USR001";
		}
		else if (!dto.getName().equals("")) {
			List<User> list = userService.FindByUserName(bean.getName());
			System.out.print(list.size());
			model.addAttribute("userlist", list);
			return "USR001";
		}
		
		List<User> list = userService.getAllUsers();
		if (list.size() == 0)
			model.addAttribute("msg", "User not found!");
		else
			model.addAttribute("userlist", list);
			return "USR001";
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public ModelAndView adduser() {
		return new ModelAndView ("USR002","userBean",new UserBean());
	}
	
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public String userRegister(@ModelAttribute("userBean")@Validated UserBean bean,BindingResult bs,Model model) {
		if(bs.hasErrors()){
			return "USR002";
		}
		if (bean.getPassword().equals(bean.getConfirm())) {
			User dto = new User();
			dto.setId(bean.getId());
			dto.setName(bean.getName());
			dto.setPassword(bean.getPassword());
			Optional<User> list = userService.getUserById(bean.getId());
			if (list.isPresent()) {
				model.addAttribute("user",bean);
				model.addAttribute("err", "UserId has been already exist!");
			}else {
				int res = userService.save(dto);
				if (res > 0) {
					model.addAttribute("msg", "Insert successful");
					model.addAttribute("user", new UserBean());
					return "USR001";
				}else {
					model.addAttribute("err", "Insert fail");
					return "USR001";
				}
			}
		} else
			model.addAttribute("err", "Password are not match");
		return "USR002";
	}
	
	
	
	
	@RequestMapping(value = "/userupdate", method = RequestMethod.GET)
	public String userupdate(@RequestParam("id")String id,Model model) {
		
		Optional<User> user=userService.getUserById(id);
		User dto = new User();
		dto=user.get();
		model.addAttribute("userBean", new UserBean(dto.getId(),dto.getName(),dto.getPassword(),dto.getPassword()));
		return "USR002-01";
		
	}
	
	@RequestMapping(value = "/userupdate", method = RequestMethod.POST)
	public String userupdate(@ModelAttribute("userBean")@Validated UserBean bean,BindingResult bs,Model model) {
		if(bs.hasErrors()){
			model.addAttribute("user",bean);
			model.addAttribute("err", "Fields must not be null");
			return "USR002-01";
		}
		if (bean.getPassword().equals(bean.getConfirm())) {
			User dto = new User();
			dto.setId(bean.getId());
			dto.setName(bean.getName());
			dto.setPassword(bean.getPassword());
			int res = userService.update(dto,dto.getId());
			if (res > 0)
				model.addAttribute("msg", "Update successful");
			else
				model.addAttribute("err", "Update fail");
		}else {
			model.addAttribute("err", "Password are not match");
			model.addAttribute("user",bean);
			return "USR002-01";
		}
		model.addAttribute("userBean", new UserBean());
		return "USR001";
	}
	
	@RequestMapping(value = "/userdelete", method = RequestMethod.GET)
	public String userdelete(@RequestParam("id")String id,Model model) {
		User dto = new User();
		dto.setId(id);
		int res = userService.delete(dto.getId());
		if (res > 0) {
			model.addAttribute("msg", "Delete successful");
		}else
			model.addAttribute("err", "Delete failed");
		
		model.addAttribute("userBean", new UserBean());
		return "USR001";
	}
	
}
