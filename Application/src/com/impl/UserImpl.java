package com.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.userprops.UserProps;

public class UserImpl implements User {

	DataSource dataSource;
	
	public UserImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<UserProps> listUsrs() {
		List<UserProps> listFromDB = new ArrayList<UserProps>();
		Connection con = null;
		PreparedStatement ps;
		ResultSet rs;
		String sql = "select * from users";
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				UserProps userList = new UserProps(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
				listFromDB.add(userList);
			}
			rs.close();
			ps.close();
			return listFromDB;
					
		}catch(Exception e) {
			return null;
			}
		finally {
			if(con!= null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}	
	}
}

