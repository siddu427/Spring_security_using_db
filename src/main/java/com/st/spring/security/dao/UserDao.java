package com.st.spring.security.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.st.spring.security.model.LoginUser;

@Repository
public class UserDao extends AbstractBaseRepository {

	private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);
	
	private static final String USER_DETAILS_QUERY = 
			"SELECT u.password from st_user u where u.principal=:USER_NAME";
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public LoginUser getUserDetails(String userName) {
		
		Map<String, Object> params = 
				Collections.singletonMap("USER_NAME", userName);
		
		List<LoginUser> currentUser = jdbcTemplate
				.query(USER_DETAILS_QUERY, params, 
						new RowMapper<LoginUser>() {
			@Override
			public LoginUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				LoginUser currentUser = new LoginUser(userName, rs.getString("password"));
				return currentUser;
			}
		});
		
		LoginUser user = (currentUser != null && !currentUser.isEmpty()) ? currentUser.get(0) : null;
		
		if (user != null) {
			LOG.debug("User Details is {}", user.getUsername());	
		}
		
		return user;
	}
	
}
