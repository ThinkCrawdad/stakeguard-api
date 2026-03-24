package app.com.stakeguard.repository;

import app.com.stakeguard.entity.Pick;
import app.com.stakeguard.enums.StatusPick;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickRepository extends JpaRepository<Pick, Long> {

    List<Pick> findByEstado(StatusPick estado);

    List<Pick> findByDeporte(String deporte);
}