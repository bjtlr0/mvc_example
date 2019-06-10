package com.ksh.vo.work;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data // @ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor 을 한번에 선언하는 어노테이션
public class MemberVO implements UserDetails {
	private long id;
	private String userName;
	private String userPassword;
	private Date createTime;
	private Date modifyTime;
	private List<String> roles;
//	private List<MemberRoleVO> roles; 	
	// MemberRole객체에 추가적인 항목이 들어갈 수 있으므로 객체로 만든다(description,Criteria,.. )
	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		for(int i=0; i< this.roles.size(); i++){
//			String role = this.roles.get(i).getAuthority();
//			authorities.add(new SimpleGrantedAuthority(role));
//		}
//		return authorities;
//	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(int i=0; i< this.roles.size(); i++){
			String role = this.roles.get(i);
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return this.userPassword;
	}
	@Override
	public String getUsername() {
		return this.userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

}