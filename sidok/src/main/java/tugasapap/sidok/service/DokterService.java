package tugasapap.sidok.service;

import java.util.List;
import java.util.Optional;

import tugasapap.sidok.model.DokterModel;

public interface DokterService {

    void addDokter(DokterModel dokter);

    List<DokterModel> findAllDokter();

    Optional<DokterModel> getDokterByIdDokter(Long id);

    DokterModel changeDokter(DokterModel dokterModel);

    void deleteDokter(DokterModel dokter);

    Optional<DokterModel> getDokterByNik(String nik);
}
