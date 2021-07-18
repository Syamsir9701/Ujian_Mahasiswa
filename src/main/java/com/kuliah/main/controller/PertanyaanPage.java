package com.kuliah.main.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kuliah.main.entity.MataKuliah;
import com.kuliah.main.entity.Pertanyaan;
import com.kuliah.main.repository.PertanyaanRepository;
import com.kuliah.main.utility.FileUtility;

@Controller
public class PertanyaanPage {

	@Autowired
	PertanyaanRepository pertanyaanRepo;
	
	private final String UPLOAD_DIR = "./src/main/resources/static/uploads/";
	
	@GetMapping("/pertanyaan/add")
	public String viewAddPertanyaan(Model model) {
		model.addAttribute("pertanyaan", new Pertanyaan());
		return "add_pertanyaan";
	}
	
	@GetMapping("/pertanyaan/view")
	public String viewIndexPertanyaan(Model model) {
		model.addAttribute("listpertanyaan",pertanyaanRepo.getAllPertanyaan());
		model.addAttribute("active",4);
		return "view-pertanyaan";
	}
	
	@GetMapping("/pertanyaan/update/{id}")
	public String viewUpdatePertanyaan(@PathVariable String id, Model model) {
		Pertanyaan pertanyaan = pertanyaanRepo.cariPertanyaan(id);
		model.addAttribute("pertanyaan", pertanyaan);
		
		return "add_pertanyaan";
	}
	
	@GetMapping("/pertanyaan/delete/{id}")
	public String deletePertanyaan(@PathVariable String id, Model model) {
		this.pertanyaanRepo.deletePertanyaan(id);
		
		return"redirect:/pertanyaan/view";
	}
	
	@PostMapping("/pertanyaan/vieew")
	public String addPertanyaan(@RequestParam(value = "file")MultipartFile file, @ModelAttribute Pertanyaan pertanyaan, Model model) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		String uploadDir = "/";
		FileUtility.saveFile(uploadDir, fileName, file);
		pertanyaan.setStatusGambar("/"+uploadDir + fileName);
		this.pertanyaanRepo.addPertanyaan(pertanyaan);
		model.addAttribute("listpertanyaan",pertanyaanRepo.getAllPertanyaan());
		
		return"redirect:/pertanyaan/view";
	}
	
}