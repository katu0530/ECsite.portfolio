package com.example.app.mapper;

import java.util.List;

import com.example.app.domain.Member;

public interface ItemMapper {
	
	List<Member> selectAll() throws Exception;
	Member selectById(Integer id) throws Exception;
	void insert(Member member) throws Exception;
	void update(Member member) throws Exception;
	void delete(Integer id) throws Exception;

}
