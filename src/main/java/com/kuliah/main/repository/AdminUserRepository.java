package com.kuliah.main.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.AdminUser;
import com.kuliah.main.entity.Mahasiswa;

public interface AdminUserRepository extends CrudRepository<AdminUser, Long> {
	public AdminUser findByUsername(String username);
	public List<AdminUser> getAllAdminUser();
	
	public AdminUser addAdminUser(AdminUser mahasiswa);
	public AdminUser getAdminUserById(String id);
	public void deleteAdminUser(String id);
	
	
	
	
}
