package tugasapap.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasapap.sidok.model.PoliModel;
import tugasapap.sidok.repository.PoliDb;

import javax.transaction.Transactional;
import java.util.List;

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
}
