package tugasapap.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tugasapap.sidok.model.JadwalJagaModel;

import java.util.List;

public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long> {

    List<JadwalJagaModel> findAll();
}
