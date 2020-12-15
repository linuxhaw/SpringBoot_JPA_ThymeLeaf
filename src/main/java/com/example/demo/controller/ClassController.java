package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.dto.Class;
import com.example.demo.dao.ClassService;
import com.example.demo.model.ClassBean;


@Controller
public class ClassController {
	
	@Autowired
	 private ClassService classService;
	
	@ModelAttribute("classBean")
	public ClassBean getClassBean() {
		return new ClassBean();
	}

	
	@RequestMapping(value="/addclass", method=RequestMethod.GET)
	public ModelAndView addclass() {
		return new ModelAndView("BUD003","classBean",new ClassBean());
	}
	
	@RequestMapping(value="/ClassRegister",method=RequestMethod.POST)
	public String ClassRegister(@ModelAttribute("classBean")@Validated ClassBean bean,BindingResult bs,ModelMap model) {
		if(bs.hasErrors()) {
			model.addAttribute("class", bean);
			return "BUD003";
		}
		Class dto=new Class();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		
		Optional<Class> list = classService.getUserById(dto.getId());
		if (list.isPresent())
			model.addAttribute("err", "Class has been already exist!");
		else{
			int res = classService.save(dto);
			if (res > 0)
				model.addAttribute("msg", "Insert successful");
			else
				model.addAttribute("err", "Insert fail");
		}
		
		return "BUD003";
	}
	
}
