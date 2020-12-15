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

import com.example.demo.dao.StudentService;
import com.example.demo.dto.Student;
import com.example.demo.model.StudentBean;


@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@ModelAttribute("studentBean")
	public StudentBean getStudentBean() {
		return new StudentBean();
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) {
		return "BUD001";
	}
	
	@RequestMapping(value = "/studentsearch", method = RequestMethod.POST)
	public String studentsearch(@ModelAttribute("studentBean") StudentBean bean,Model model) {
	
		
		Student dto = new Student();
		dto.setStudentId(bean.getId());
		dto.setStudentName(bean.getName());
		dto.setClassName(bean.getClassName());
		
		if (!dto.getStudentId().equals("")) {
			List<Student> list = studentService.findAllByStudentId(bean.getId());
			System.out.print(list.size());
			model.addAttribute("studentlist", list);
			return "BUD001";
		}
		else if (!dto.getStudentName().equals("")) {
			List<Student> list = studentService.findAllByStudentName(bean.getName());
			System.out.print(list.size());
			model.addAttribute("studentlist", list);
			return "BUD001";
		}
		else if (!dto.getClassName().equals("")){
			List<Student> list = studentService.findAllByClassName(bean.getClassName());
			System.out.print(list.size());
			model.addAttribute("studentlist", list);
			return "BUD001";
		}
		
		List<Student> list = studentService.getAllStudents();
		if (list.size() == 0)
			model.addAttribute("msg", "Student not found!");
		else
			model.addAttribute("studentlist", list);
			return "BUD001";
	}
	
	@RequestMapping(value = "/addstudent", method = RequestMethod.GET)
	public String addstudent(Model model) {
		model.addAttribute("studentBean", new StudentBean());
		return "BUD002";
	}
	
	@RequestMapping(value = "/studentRegister", method = RequestMethod.POST)
	public String studentRegister(@ModelAttribute("studentBean")@Validated StudentBean bean,BindingResult bs,Model model) {
		if(bs.hasErrors()) {
			return "BUD002";
		}
		Student dto = new Student();
		dto.setStudentId(bean.getId());
		dto.setStudentName(bean.getName());
		dto.setStatus(bean.getStatus());
		dto.setClassName(bean.getClassName());
		dto.setRegisterDate(bean.getYear() + "-" + bean.getMonth() + "-" + bean.getDay());
		Optional<Student> list = studentService.getStudentById(dto.getStudentId());
		if (list.isPresent())
			model.addAttribute("err", "StudentId has been already exist!");
		else {
			int res = studentService.save(dto);
			if (res > 0)
				model.addAttribute("msg", "Insert successful");
			else {
				model.addAttribute("err", "Insert fail");
				return "BUD002";
			}
		}
		return "BUD002";
	}
	
	@RequestMapping(value = "/studentUpdate", method = RequestMethod.GET)
	public String studentUpdate(@RequestParam("name")String name,Model model) {
		Optional<Student> res = studentService.getStudentById(name);
		Student dto = new Student();
		dto.setStudentName("");
		dto.setStudentId(name);
		dto.setClassName("");
		dto=res.get();
		StudentBean bean = new StudentBean();
		bean.setId(dto.getStudentId());
		bean.setName(dto.getStudentName());
		bean.setClassName(dto.getClassName());
		bean.setYear(dto.getRegisterDate().substring(0, 4));
		String month=dto.getRegisterDate().substring(5, 7);
		String mo=month.substring(0,1);
		if(mo.equals("0")) {
			bean.setMonth(month.substring(1,2));
		}else {
			bean.setMonth(month);
		}
		String date=dto.getRegisterDate().substring(8, 10);
		String da=date.substring(0,1);
		if(da.equals("0")) {
			bean.setDay(date.substring(1,2));
		}else {
			bean.setDay(date);
		}
		bean.setStatus(dto.getStatus());
		model.addAttribute("studentBean", bean);
		return "BUD002-01";
	}
	
	@RequestMapping(value = "/studentUpdate", method = RequestMethod.POST)
	public String studentUpdate(@ModelAttribute("studentBean")@Validated StudentBean bean,BindingResult bs,Model model) {
		if(bs.hasErrors()) {
			return "BUD002-01";
		}
		Student dto = new Student();
		dto.setStudentId(bean.getId());
		dto.setStudentName(bean.getName());
		dto.setStatus(bean.getStatus());
		dto.setClassName(bean.getClassName());
		dto.setRegisterDate(bean.getYear() + "-" + bean.getMonth() + "-" + bean.getDay());
		int res = studentService.update(dto, dto.getStudentId());
		if (res > 0)
			model.addAttribute("msg", "Update successful");
		else
			model.addAttribute("err", "Update fail");
			return "BUD002-01";
	}
	
	@RequestMapping(value = "/studentDelete", method = RequestMethod.POST)
	public String studentDelete(@ModelAttribute("studentBean")StudentBean bean,Model model) {
		Student dto = new Student();
		dto.setStudentId(bean.getId());
		int res = studentService.delete(dto.getStudentId());
		if (res > 0)
			model.addAttribute("msg", "Delete successful");
		else
			model.addAttribute("err", "Delete fail");
			return "BUD002-01";
	}
	
	
}
