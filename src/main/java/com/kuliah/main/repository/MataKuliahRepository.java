package com.kuliah.main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.Dosen;
import com.kuliah.main.entity.MataKuliah;

public interface MataKuliahRepository extends CrudRepository<MataKuliah, Long> {
	public List<MataKuliah> getAllMataKuliah();
	public void deleteMataKuliah(String id);
	public MataKuliah addMataKuliah(MataKuliah matakuliah);
	public MataKuliah getMataKuliahByName(String name);
	public MataKuliah getMataKuliahById(String id);
	public MataKuliah findByNamaMataKuliah(String nama);
	public MataKuliah findByIdMataKuliah(Long id);

}
