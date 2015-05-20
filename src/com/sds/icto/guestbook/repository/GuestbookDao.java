package com.sds.icto.guestbook.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.sds.icto.guestbook.domain.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	
	public void insertGuestBook(GuestbookVo vo){
		sqlMapClientTemplate.insert("guestbook.insert", vo);
	}
	
	public void deleteGuestBook(GuestbookVo vo){
		sqlMapClientTemplate.delete("guestbook.delete", vo);
	}
	
	public GuestbookVo selectGuestBookByNo(Long no){
		GuestbookVo vo = (GuestbookVo) sqlMapClientTemplate.queryForObject("guestbook.selectByNo", no);
		return vo;
	}
	
	@SuppressWarnings("unchecked")
	public List<GuestbookVo> selectGuestBook(){
		List<GuestbookVo> list = sqlMapClientTemplate.queryForList("guestbook.selectAll");
		return list;
	}
	
	
}
