package tugasapap.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tugasapap.sidok.model.PoliModel;

import java.util.List;

public interface PoliDb extends JpaRepository<PoliModel, Long> {

    List<PoliModel> findAll();
}
