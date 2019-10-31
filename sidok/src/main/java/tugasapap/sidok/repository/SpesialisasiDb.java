package tugasapap.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tugasapap.sidok.model.SpesialisasiModel;

import java.util.List;

public interface SpesialisasiDb extends JpaRepository<SpesialisasiModel, Long> {

    List<SpesialisasiModel> findAll();
}
