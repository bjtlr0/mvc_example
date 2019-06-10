package com.ksh;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.http.FormLoginBeanDefinitionParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ksh.mapper.engine.SigPSRuleMapperDao;
import com.ksh.mapper.work.MemberMapperDao;
import com.ksh.vo.work.MemberRole;
import com.ksh.vo.work.MemberRoleVO;
import com.ksh.vo.work.MemberVO;

@RunWith(SpringRunner.class)
@SpringBootTest // SpringBootTest는 아래처럼 autowired를 통한 DI가 되지만.. 
// SpringJ4Unit에서는 컨택스트패스, 클래스패스등.. ContextConfiguration을 직접 연결해줘야만 bean이 등록된다.
public class KshApplicationTests {
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private SigPSRuleMapperDao ruleDao;

	@Autowired
	private MemberMapperDao memberDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private MockMvc mock;

	@Test
	@Ignore
	public void templateTest() {
		long i = 1023;
		System.out.println(ruleDao.getSigPSRule(i).getSignature());
	}

	@Test
	@Ignore
	public void insertTest() {
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
	@Ignore
	public void selectTest() {
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

	@Before
	public void setup(){
		mock = MockMvcBuilders.webAppContextSetup(context)
				.addFilters(springSecurityFilterChain)
				.build();
	}
	
	// TODO : Login Test
	@Test
	public void loginTest() {
		try {
//			mock.perform(formLogin().user("ksh123").password("ksh123"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
