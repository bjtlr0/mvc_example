package com.ksh.test.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.FormLoginBeanDefinitionParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.ksh.database.mapper.MemberMapperDao;
import com.ksh.database.vo.MemberRole;
import com.ksh.database.vo.MemberRoleVO;
import com.ksh.database.vo.MemberVO;

@RunWith(SpringRunner.class)
@SpringBootTest // SpringBootTest는 아래처럼 autowired를 통한 DI가 되지만.. 
// SpringJ4Unit에서는 컨택스트패스, 클래스패스등.. ContextConfiguration을 직접 연결해줘야만 bean이 등록된다.
@Transactional
public class LoginControllerTest {
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private Filter springSecurityFilterChain;

	private MockMvc mock;
	private MockHttpSession session;

	@Before
	public void setup() throws Exception{
		mock = MockMvcBuilders.webAppContextSetup(context)
				.dispatchOptions(true)
				.addFilters(springSecurityFilterChain)
				.build();
		
		// test를 위해 session 을 미리 생성한다.
		this.session = (MockHttpSession) mock.perform(formLogin("/login.do")
				.user("q-user-name", "ksh123")
				.password("q-user-password", "ksh123"))
				.andExpect(status().is3xxRedirection())
				.andReturn().getRequest().getSession();
	}
	
	/**
	 * mockHttpSession을 맺었다는 전제하에 테스트 하기 위한 함수
	 * */
	@Test
	public void afterLoginTest() throws Exception{
		mock.perform(post("/afterLogin.do")
				.session(this.session)
				.with(csrf().asHeader())) // 이런것도 가능하네 ..
		.andDo(print());
	}
	
	/**
	 * Login이 이뤄지는지 확인하기 위한 함수
	 * */
	
	@Test
	@Ignore
	public void loginTest() throws Exception{
		// header 설정(contentType과 같은..)이 필요하다면 httpHeaders를 생성해서 추가해야 된다.
		
		/*mock.perform(post("/login.do").param("q-user-name","ksh123").param("q-user-password", "ksh123")
				).andDo(print()).andExpect(status().isOk());*/
		mock.perform(formLogin("/login.do").user("q-user-name","ksh123").password("q-user-password","ksh123")
				).andDo(print());
	}
}
