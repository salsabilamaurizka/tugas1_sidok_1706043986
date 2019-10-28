package tugasapap.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tugasapap.sidok.model.DokterModel;
import tugasapap.sidok.model.JadwalJagaModel;
import tugasapap.sidok.model.PoliModel;

import java.util.List;

public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long> {

    List<JadwalJagaModel> findJadwalJagaByDokter(DokterModel dokter);

    List<JadwalJagaModel> findJadwalJagaByPoli(PoliModel poli);
}
