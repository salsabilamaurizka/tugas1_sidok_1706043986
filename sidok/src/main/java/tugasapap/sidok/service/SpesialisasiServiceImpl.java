package tugasapap.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasapap.sidok.model.PoliModel;
import tugasapap.sidok.model.SpesialisasiModel;
import tugasapap.sidok.repository.PoliDb;
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
    public List<SpesialisasiModel> findAllSpesialisasi() {
        return spesialisasiDb.findAll();
    }

    @Override
    public Optional<SpesialisasiModel> getSpesialisasiById(Long idSpesialisasi) {
        return spesialisasiDb.findById(idSpesialisasi);
    }
}
