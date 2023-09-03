package com.UserManagement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.UserManagement.dao.UserDao;
import com.UserManagement.exception.InvalidUserException;
import com.UserManagement.model.User;
import com.UserManagement.repository.UserRepository;




@SpringBootTest
class UserManagementApplicationTests {
	@Autowired
	UserRepository uRepo;
	
	@Autowired
	UserDao uDao;
	
//	@Test
//	void contextLoads() {
//	}
	
	@Test
	public void testGetAllUsers() throws InvalidUserException {
		List<User> list = uDao.getAllUsers();
		assertThat(list).size().isGreaterThan(0);
		
	}
	
	
	
	@Test
	public void testDeleteUser() throws InvalidUserException {
		uDao.deleteUser(5);
		assertThat(uRepo.existsById(5)).isFalse();
	}

}
