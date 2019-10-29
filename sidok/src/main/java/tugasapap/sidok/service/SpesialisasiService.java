package tugasapap.sidok.service;

import tugasapap.sidok.model.SpesialisasiModel;

import java.util.List;
import java.util.Optional;

public interface SpesialisasiService {

    List<SpesialisasiModel> getSpesialisasiList();

    Optional<SpesialisasiModel> getSpesialisasiById(Long idSpesialisasi);
}
