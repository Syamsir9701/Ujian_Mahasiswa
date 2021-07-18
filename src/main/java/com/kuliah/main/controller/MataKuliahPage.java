package com.kuliah.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.MataKuliah;
import com.kuliah.main.repository.MataKuliahRepository;


@Controller
public class MataKuliahPage {
	
	@Autowired
	MataKuliahRepository matakuliahRepo;
	
	
	@GetMapping("/matakuliah/view")
	public String viewIndexMataKuliah(Model model) {
		
		model.addAttribute("listMataKuliah",matakuliahRepo.getAllMataKuliah());
		model.addAttribute("active",3);
		return "view_matakuliah";
	}
	
	
	@GetMapping("/matakuliah/add")
	public String viewAddMataKuliah(Model model) {
		
		// buat penampung data MataKuliah di halaman htmlnya
		model.addAttribute("matakuliah",new MataKuliah());
		
		return "add_matakuliah";
	}
	
	@PostMapping("/matakuliah/view")
	  public String addMataKuliah(@ModelAttribute MataKuliah MataKuliah, Model model) {
		
		// buat penampung data MataKuliah di halaman htmlnya
		this.matakuliahRepo.addMataKuliah(MataKuliah);
		model.addAttribute("listMataKuliah",matakuliahRepo.getAllMataKuliah());
		
		
		return "redirect:/matakuliah/view";
	}
	
	
	@GetMapping("/matakuliah/update/{id}")
	public String viewUpdateMataKuliah(@PathVariable String id, Model model) {
		
		MataKuliah MataKuliah = matakuliahRepo.getMataKuliahById(id);
		// buat penampung data MataKuliah di halaman htmlnya
		model.addAttribute("matakuliah",MataKuliah);
		
		return "add_matakuliah";
	}
	
	@GetMapping("/matakuliah/delete/{id}")
	public String deleteMataKuliah(@PathVariable String id, Model model) {
		
		this.matakuliahRepo.deleteMataKuliah(id);
		model.addAttribute("listMataKuliah",matakuliahRepo.getAllMataKuliah());
		
		
		return "redirect:/matakuliah/view";
	}

}
