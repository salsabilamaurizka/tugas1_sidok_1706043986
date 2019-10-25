package tugasapap.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugasapap.sidok.model.JadwalJagaModel;
import tugasapap.sidok.repository.JadwalJagaDb;

import java.util.List;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService{

    @Autowired
    private JadwalJagaDb jadwalJagaDb;

    @Override
    public void addJadwalJaga(JadwalJagaModel jadwalJaga) {
        jadwalJagaDb.save(jadwalJaga);
    }

    public List<JadwalJagaModel> findAllJadwalJagaByIdDokter() {
        return jadwalJagaDb.findAll();
    }
}
