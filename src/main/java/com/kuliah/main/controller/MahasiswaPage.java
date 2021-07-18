package com.kuliah.main.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Mahasiswa;
import com.kuliah.main.repository.MahasiswaRepository;


import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class MahasiswaPage {
	
	@Autowired
	MahasiswaRepository mahasiswaRepo;
	

	@GetMapping("/mahasiswa/add")
	public String viewAddMahasiswa(Model model) {
		model.addAttribute("mahasiswa", new Mahasiswa());
		
		return "add_mahasiswa";
	}
	
	
	@GetMapping("/mahasiswa/view")
	public String viewIndexMahasiswa(Model model) {
		model.addAttribute("listMahasiswa",mahasiswaRepo.getAllMahasiswa());
		model.addAttribute("active",1);
		model.addAttribute("test","Test Aja");
		
		return "view_mahasiswa";
		

	}
	
	@GetMapping("/mahasiswa/update/{id}")
	public String viewUpdateMahasiswa(@PathVariable String id, Model model) {
		
		Mahasiswa mahasiswa = mahasiswaRepo.getMahasisswaById(id);
		model.addAttribute("mahasiswa", mahasiswa);
		
		return "add_mahasiswa";
	}
	
	@GetMapping("/mahasiswa/delete/{id}")
	public String deleteMahasiswa(@PathVariable String id, Model model) {
		this.mahasiswaRepo.deleteMahasiswa(id);
		model.addAttribute("listMahasiswa", mahasiswaRepo.getAllMahasiswa());
		
		return "redirect:/mahasiswa/view";
	}
	
	@PostMapping("/mahasiswa/view")
	public String addMahasiswa(@ModelAttribute Mahasiswa mahasiswa, Model model) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = mahasiswa.getPassword();
		String encodedPassword = passwordEncoder.encode(plainPassword);
		mahasiswa.setPassword(encodedPassword);
		this.mahasiswaRepo.addMahasisssswa(mahasiswa);
		model.addAttribute("listMahasiswa",mahasiswaRepo.getAllMahasiswa());
		
		return "redirect:/mahasiswa/view";
	}
}
