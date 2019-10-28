package tugasapap.sidok.service;

import tugasapap.sidok.model.DokterModel;
import tugasapap.sidok.model.JadwalJagaModel;
import tugasapap.sidok.model.PoliModel;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface JadwalJagaService {

    void addJadwalJaga(JadwalJagaModel jadwalJaga);

    List<JadwalJagaModel> getJadwalJagaByDokter(DokterModel dokter);

    List<JadwalJagaModel> getJadwalJagaByPoli(PoliModel poliModel);

}
