package tugasapap.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tugasapap.sidok.model.PoliModel;

import java.util.List;
import java.util.Optional;

public interface PoliDb extends JpaRepository<PoliModel, Long> {

    List<PoliModel> findAll();

    Optional<PoliModel> findById(Long idPoli);
}
