package com.sds.icto.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.guestbook.dao.GuestbookDao;
import com.sds.icto.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {

	@Autowired
	GuestbookDao guestbookDao;
	
	@RequestMapping("/index")
	public String index(Model model){
		List<GuestbookVo> list = guestbookDao.selectGuestBook();
		model.addAttribute("list", list);
		return "/views/index.jsp";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestParam String name, @RequestParam("pass") String password, @RequestParam("content") String message){

		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		guestbookDao.insertGuestBook(vo);
		
		return "redirect:/index";
	}
/*	@RequestMapping(value="/delete")
	public String delete(@RequestParam String no, Model model){
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(Long.parseLong(no));
		model.addAttribute("no",no);
		guestbookDao.deleteGuestBook(vo);
		
		return "/views/deleteform.jsp";
	}*/
}
