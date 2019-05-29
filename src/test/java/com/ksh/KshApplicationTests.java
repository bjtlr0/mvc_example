package com.ksh;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ksh.mapper.engine.SigPSRuleMapperDao;
import com.ksh.mapper.work.MemberMapperDao;
import com.ksh.vo.work.MemberRole;
import com.ksh.vo.work.MemberRoleVO;
import com.ksh.vo.work.MemberVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KshApplicationTests {
	@Autowired
	SigPSRuleMapperDao ruleDao;
	
	@Autowired
	MemberMapperDao memberDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test @Ignore
	public void templateTest(){
		long i = 1023;
		System.out.println(ruleDao.getSigPSRule(i).getSignature());
	}
	
	@Test
	public void understandMock (){
		
	}
	
	@Test @Ignore
	public void selectTest(){
		MemberVO member = new MemberVO();
		try{
			member = memberDao.selectMember(3);
			if (member != null) {
				System.out.println("select success");
			}else{
				System.out.println("fail");
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@Test
	public void insertTest(){
		MemberVO member = new MemberVO();
		member.setUserId("ksh123");
		member.setUserPwd(passwordEncoder.encode("ksh123"));
		member.setCreateTime(new Date(System.currentTimeMillis()));
		member.setModifyTime(new Date(System.currentTimeMillis()));
		
		MemberRoleVO basicRole = new MemberRoleVO();
		basicRole.setRoleNum(1);
		// basicRole.setRole(MemberRole.BASIC);
		basicRole.setRole(MemberRole.ROLE_BASIC);
		
		MemberRoleVO managerRole = new MemberRoleVO();
		managerRole.setRoleNum(2);
		managerRole.setRole(MemberRole.ROLE_MANAGER);
		//managerRole.setRole(MemberRole.MANAGER);
		
		List<MemberRoleVO> roles = new ArrayList<MemberRoleVO>();
		roles.add(basicRole);
		roles.add(managerRole);
		member.setRoles(roles);
		
		try{
			if (memberDao.insertMember(member) > 0){
				System.out.println("insert user " + member.getId() + " , " + member.getPassword());
			}else {
				System.out.println("fail");
			}	
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
