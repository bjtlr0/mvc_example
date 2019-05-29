package com.ksh.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import com.ksh.vo.work.MemberRole;
import com.ksh.vo.work.MemberRoleVO;


/**
 * 사실 memberRole객체가 Number를 가질 필요성도 없고
 * 그로 인해 &로 강제로 나누는 행위를 하는게 엄청 거슬리지만
 * 그냥 테스트니까 진행한다.
 * */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListTypeHandler implements TypeHandler<List<MemberRoleVO>> {

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
	}

}
