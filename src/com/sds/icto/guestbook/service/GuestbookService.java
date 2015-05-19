package com.sds.icto.guestbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.guestbook.domain.GuestbookVo;
import com.sds.icto.guestbook.repository.GuestbookDao;

@Service
public class GuestbookService {

	@Autowired
	GuestbookDao guestbookDao;
	
	public void addGuestbook(GuestbookVo vo){
		guestbookDao.insertGuestBook(vo);
	}
	public void removeGuestbook(GuestbookVo vo){
		guestbookDao.deleteGuestBook(vo);
	}
	public GuestbookVo getGuestbookByNo(Long no){
		GuestbookVo gb = guestbookDao.selectGuestBookByNo(no);
		return gb;
	}
	public List<GuestbookVo> getGuestbook(){
		List<GuestbookVo> list = guestbookDao.selectGuestBook();
		return list;
	}
	
	
}
