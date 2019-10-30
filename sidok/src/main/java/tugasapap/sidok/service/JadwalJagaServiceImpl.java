package tugasapap.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugasapap.sidok.model.DokterModel;
import tugasapap.sidok.model.JadwalJagaModel;
import tugasapap.sidok.model.PoliModel;
import tugasapap.sidok.repository.DokterDb;
import tugasapap.sidok.repository.JadwalJagaDb;

import java.util.List;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService{

    @Autowired
    private JadwalJagaDb jadwalJagaDb;

    @Autowired
    private DokterDb dokterDb;

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

    @Override
    public List<DokterModel> findAllDokterByIdPoli(Long idPoli) {
        return dokterDb.findAll();
    }

    @Override
    public DokterModel findMostDokter(Long idPoli) {
        List<DokterModel> listDokterPoli = findAllDokterByIdPoli(idPoli);
        DokterModel hasilDokter = new DokterModel();
        int jumlah = 0;
        for (DokterModel i : listDokterPoli) {
            int temp = 0;
            for (DokterModel j : listDokterPoli) {
                if (i.equals(j)) {
                    temp++;
                }
            }
            if (temp > jumlah) {
                hasilDokter = i;
            }
        }
        return hasilDokter;
    }
}
