package com.eval.coronakit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eval.coronakit.entity.KitDetail;

@Repository
public interface KitDetailRepository extends JpaRepository<KitDetail, Integer>{
	
	boolean existsByCoronaKitId(int coronaKitId);
	
	@Query("SELECT k FROM KitDetail k WHERE k.coronaKitId=:coronaKitId")
	List<KitDetail> findAllCoronaKitId(int coronaKitId);

}
