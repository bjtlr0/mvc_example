package com.ksh.database.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

/*import com.ksh.vo.work.MemberRole;
import com.ksh.vo.work.MemberRoleVO;*/



@MappedJdbcTypes(JdbcType.VARCHAR)
public class RoleTypeHandler implements TypeHandler<List<String>> {
	@Override
	public void setParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType)
			throws SQLException {
		StringBuffer buf = new StringBuffer();
		for(int j=0; j<parameter.size(); j++){
			buf.append(parameter.get(j).trim());
			if(j<parameter.size() - 1){
				buf.append(",");
			}
		}
		ps.setString(i,buf.toString().trim());
	}

	@Override
	public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
		List<String> roles = new ArrayList<String>();
		String[] raw = rs.getString(columnName).split(",");
		for (int j=0; j<raw.length; j++){
			roles.add(raw[j].trim());
		}
		return roles;
	}

	@Override
	public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
		List<String> roles = new ArrayList<String>();
		String[] raw = rs.getString(columnIndex).split(",");
		for (int j=0; j<raw.length; j++){
			roles.add(raw[j].trim());
		}
		return roles;
	}

	@Override
	public List<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * memberRole객체를 list로 받는경우 괜히 복잡해진다.
	 * ROLE항목만 받을꺼다.
	 */
	/*
	@Override
	public void setParameter(PreparedStatement ps, int i, List<MemberRoleVO> parameter, JdbcType jdbcType)
			throws SQLException {
		StringBuffer buf = new StringBuffer();
		for(int k=0; k<parameter.size(); k++){
			MemberRoleVO role = parameter.get(k);
			//buf.append(role.getAuthority());
			buf.append(role.getRoleNum() + "&" + role.getAuthority());
			if(k < parameter.size() -1){
				buf.append(",");
			}
		}
		ps.setString(i, buf.toString().trim());
	}

	@Override
	public List<MemberRoleVO> getResult(ResultSet rs, String columnName) throws SQLException {
		List<MemberRoleVO> list = new ArrayList<MemberRoleVO>();
		String[] tmp = rs.getString(columnName).split(",");
		for(int i=0; i< tmp.length; i++){
			long roleNum = Long.valueOf(tmp[i].split("&")[0]);
			String role = tmp[i].split("&")[1].trim();
//			String role = tmp[i];
			MemberRoleVO roleVO = new MemberRoleVO();
			roleVO.setRoleNum(roleNum);
			roleVO.setRole(MemberRole.valueOf(role));
			list.add(roleVO);
		}
		return list;
	}
	@Override
	public List<MemberRoleVO> getResult(ResultSet rs, int columnIndex) throws SQLException {
		List<MemberRoleVO> list = new ArrayList<MemberRoleVO>();
		String[] tmp = rs.getString(columnIndex).split(",");
		for(int i=0; i< tmp.length; i++){
			long roleNum = Long.valueOf(tmp[i].split("&")[0]);
			String role = tmp[i].split("&")[1].trim();
			MemberRoleVO roleVO = new MemberRoleVO();
			roleVO.setRoleNum(roleNum);
			roleVO.setRole(MemberRole.valueOf(role));
			list.add(roleVO);
		}
		return list;
	}
	@Override
	public List<MemberRoleVO> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// ???
		return null;
	}*/
}
