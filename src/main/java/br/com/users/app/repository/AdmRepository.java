package br.com.users.app.repository;

import br.com.users.app.entity.AdmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdmRepository extends JpaRepository<AdmEntity, UUID> {
    Optional<AdmEntity> findByEmail(String email);
}
