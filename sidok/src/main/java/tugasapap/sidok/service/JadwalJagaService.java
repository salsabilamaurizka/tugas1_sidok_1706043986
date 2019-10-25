package tugasapap.sidok.service;

import tugasapap.sidok.model.JadwalJagaModel;

import java.util.List;

public interface JadwalJagaService {

    void addJadwalJaga(JadwalJagaModel jadwalJaga);

    List<JadwalJagaModel> findAllJadwalJagaByIdDokter();
}
