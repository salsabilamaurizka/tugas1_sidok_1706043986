package tugasapap.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasapap.sidok.model.DokterModel;
import tugasapap.sidok.repository.DokterDb;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DokterServiceImpl implements DokterService{

    @Autowired
    private DokterDb dokterDb;

    @Override
    public void addDokter(DokterModel dokter) {
        dokterDb.save(dokter);
    }

    public List<DokterModel> getDokterList() {
        return dokterDb.findAll();
    }

    @Override
    public Optional<DokterModel> getDokterByNik(String nik) {
        return dokterDb.findByNik(nik);
    }

    @Override
    public DokterModel changeDokter(DokterModel dokterModel) {
        return null;
    }

    @Override
    public void deleteDokter(DokterModel dokter) {
        dokterDb.delete(dokter);
    }

    @Override
    public List<DokterModel> findAllDokter() {
        return dokterDb.findAll();
    }

    @Override
    public Optional<DokterModel> getDokterByIdDokter(Long idDokter) {
        return dokterDb.findById(idDokter);
    }

}
