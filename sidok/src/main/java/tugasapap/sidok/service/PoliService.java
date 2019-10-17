package tugasapap.sidok.service;

import tugasapap.sidok.model.PoliModel;

import java.util.List;

public interface PoliService {

    void addPoli(PoliModel poli);

    List<PoliModel> findAllPoli();
}
