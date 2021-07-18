package com.kuliah.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.Pertanyaan;
import com.kuliah.main.entity.Pertanyaan;

public interface PertanyaanRepository extends CrudRepository<Pertanyaan, Long>{
	public List<Pertanyaan> getAllPertanyaan();
	public Pertanyaan addPertanyaan(Pertanyaan mahasiswa);
	public void deletePertanyaan(String id);
	public Pertanyaan cariPertanyaan (String id);
	
	

}
