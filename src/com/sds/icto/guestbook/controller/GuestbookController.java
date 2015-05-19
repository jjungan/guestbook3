package com.sds.icto.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.guestbook.domain.GuestbookVo;
import com.sds.icto.guestbook.service.GuestbookService;

@Controller
public class GuestbookController {

	@Autowired
	GuestbookService guestbookService;
	
	@RequestMapping("/index")
	public String index(Model model){
		List<GuestbookVo> list = guestbookService.getGuestbook();
		model.addAttribute("list", list);
		return "index";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(GuestbookVo vo){
		guestbookService.addGuestbook(vo);
		return "redirect:/index";
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteForm(GuestbookVo vo, Model model){
		model.addAttribute("vo",vo);
		return "deleteform";
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVo vo, Model model){
		GuestbookVo gb = guestbookService.getGuestbookByNo(vo.getNo());
		if(!gb.getPassword().equals(vo.getPassword())){
			model.addAttribute("vo",vo);
			model.addAttribute("msg","비밀번호를 확인해주세요");
			return "deleteform";
		}
		guestbookService.removeGuestbook(gb);
		return "redirect:/index";
	}
}
