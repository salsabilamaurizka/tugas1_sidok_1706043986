package tugasapap.sidok.service;

import tugasapap.sidok.model.PoliModel;

import java.util.List;
import java.util.Optional;

public interface PoliService {

    void addPoli(PoliModel poli);

    List<PoliModel> findAllPoli();

    Optional<PoliModel> getPoliById(Long idPoli);

    void deletePoli(PoliModel poli);

    Optional<PoliModel> getDokterByIdPoli(Long idPoli);

    List<PoliModel> getPoliList();

    PoliModel changePoli(PoliModel poliModel);
}
