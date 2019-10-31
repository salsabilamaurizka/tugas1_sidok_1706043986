package tugasapap.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasapap.sidok.model.SpesialisasiModel;
import tugasapap.sidok.repository.SpesialisasiDb;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpesialisasiServiceImpl implements SpesialisasiService{

    @Autowired
    private SpesialisasiDb spesialisasiDb;

    @Override
    public List<SpesialisasiModel> getSpesialisasiList() {
        return spesialisasiDb.findAll();
    }

    @Override
    public Optional<SpesialisasiModel> getSpesialisasiById(Long idSpesialisasi) {
        return spesialisasiDb.findById(idSpesialisasi);
    }

    @Override
    public void setJumlahDokterSpesialisasi() {
        List<SpesialisasiModel> listSpesialisasi = getSpesialisasiList();
        for (SpesialisasiModel spesialisasi : listSpesialisasi) {
            spesialisasi.setJumlahDokter(spesialisasi.getListDokter().size());
        }
    }

}
