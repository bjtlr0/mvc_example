package com.ksh.test.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ksh.database.mapper.MemberMapperDao;
import com.ksh.database.vo.MemberRole;
import com.ksh.database.vo.MemberVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberDaoTest {
	@Autowired
	private MemberMapperDao memberDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Test
	public void memberInsertTest(){
		MemberVO member = new MemberVO();
		member.setUserName("ksh123");
		member.setUserPassword(passwordEncoder.encode("ksh123"));
		member.setCreateTime(new Date(System.currentTimeMillis()));
		member.setModifyTime(new Date(System.currentTimeMillis()));
		List<String> roles = new ArrayList<String>();
		roles.add("USER");
		member.setRoles(roles);
		try {
			if (memberDao.insertMember(member) > 0) {
				System.out.println("[USER INSERT SUCCESS] : " + member.getId() + " , " + member.getUsername()
						+ ", password : " + member.getPassword());
			} else {
				System.out.println("[USER INSERT FAILED]");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Test
	public void memberSelectTest() {
		MemberVO member = new MemberVO();
		try {
			member = memberDao.selectMember(3);
			if (member != null) {
				System.out.println("[USER SELECT COMPLETE] : User Idx : " + member.getId() + ", userName : "
						+ member.getUsername());
			} else {
				System.out.println("UserID not exists...");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
