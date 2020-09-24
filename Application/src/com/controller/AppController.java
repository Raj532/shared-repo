package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.impl.UserImpl;
import com.userprops.UserProps;

@Controller
public class AppController {

	@RequestMapping("/")
	public ModelAndView model() {
		List<UserProps> list = new ArrayList<UserProps>();
		ModelAndView mvc = new ModelAndView("displayUsers.jsp");
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/impl/bean.xml");
		UserImpl userList = context.getBean("DAOBean", UserImpl.class);
		list = userList.listUsrs();
		mvc.addObject("list", list);
		System.out.println(list);
		return mvc;
	}
}
