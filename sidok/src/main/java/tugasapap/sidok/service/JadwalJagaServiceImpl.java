package tugasapap.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugasapap.sidok.model.DokterModel;
import tugasapap.sidok.model.JadwalJagaModel;
import tugasapap.sidok.model.PoliModel;
import tugasapap.sidok.repository.JadwalJagaDb;

import java.util.List;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService{

    @Autowired
    private JadwalJagaDb jadwalJagaDb;

    @Override
    public List<JadwalJagaModel> getJadwalJagaByDokter(DokterModel dokter) {
        return jadwalJagaDb.findJadwalJagaByDokter(dokter);
    }

    @Override
    public void addJadwalJaga(JadwalJagaModel jadwalJaga) {
        jadwalJagaDb.save(jadwalJaga);
    }

    @Override
    public List<JadwalJagaModel> getJadwalJagaByPoli(PoliModel poli) {
        return jadwalJagaDb.findJadwalJagaByPoli(poli);
    }
}
