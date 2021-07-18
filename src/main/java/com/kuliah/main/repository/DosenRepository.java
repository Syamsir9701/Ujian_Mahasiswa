package com.kuliah.main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.Dosen;
import com.kuliah.main.entity.Mahasiswa;

public interface DosenRepository extends CrudRepository<Dosen, Long>{
	
	public List<Dosen> getAllDosen();
	public void deleteDosen(String id);
	public Dosen getDosenById(String id);;
	public Dosen findByNamaDosen(String nama);
	public Dosen findByIdDosen(Long id);
	public Dosen addDosen(Dosen Dosen);

}
