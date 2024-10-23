package br.com.m4rc310.persona.api.db.models.termometry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermometryDeviceRepository extends JpaRepository<TermometryDevice, String>{ 
	public List<TermometryDevice> findAllByUnityIsNull();
}
