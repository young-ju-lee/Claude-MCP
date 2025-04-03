package com.kt.csai.domain.consult.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kt.csai.domain.consult.entity.Consult;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
	List<Consult> findAllByTelNum(String telNum);
	Optional<Consult> findByConsultId(String consultId);
	List<Consult> findAllByCnvrstSttusNotAndCsttCmpNoIs(String cnvrstSttus, String csttCmpNo);
	void deleteByConsultId(String consultId);
}
