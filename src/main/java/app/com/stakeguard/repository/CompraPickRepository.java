package app.com.stakeguard.repository;

import app.com.stakeguard.entity.CompraPick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraPickRepository extends JpaRepository<CompraPick, Long> {

    List<CompraPick> findByClienteId(Long clienteId);

    List<CompraPick> findByPickId(Long pickId);
}