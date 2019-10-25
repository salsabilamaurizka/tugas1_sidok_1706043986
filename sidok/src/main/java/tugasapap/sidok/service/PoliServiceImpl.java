package tugasapap.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasapap.sidok.model.PoliModel;
import tugasapap.sidok.repository.PoliDb;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PoliServiceImpl implements PoliService{

    @Autowired
    private PoliDb poliDb;

    @Override
    public void addPoli(PoliModel poli) {
        poliDb.save(poli);
    }

    @Override
    public List<PoliModel> findAllPoli() {
        return poliDb.findAll();
    }

    @Override
    public Optional<PoliModel> getPoliById(Long idPoli) {
        return poliDb.findById(idPoli);
    }

    @Override
    public Optional<PoliModel> getAllDokterIdPoli(Long idPoli) {
        return poliDb.findById(idPoli);
    }
}
