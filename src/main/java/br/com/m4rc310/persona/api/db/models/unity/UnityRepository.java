package br.com.m4rc310.persona.api.db.models.unity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityRepository extends JpaRepository<Unity, Long> {
}
