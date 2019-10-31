package tugasapap.sidok.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugasapap.sidok.model.DokterModel;
import tugasapap.sidok.model.JadwalJagaModel;
import tugasapap.sidok.model.PoliModel;
import tugasapap.sidok.repository.DokterDb;
import tugasapap.sidok.repository.JadwalJagaDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<DokterModel> getDokterTerbanyakBertugasdiPoli(PoliModel poli) {
        try {
            List<JadwalJagaModel> jadwalJagaList = jadwalJagaDb.findJadwalJagaByPoli(poli);
            List<DokterModel> listDokterByPoli = new ArrayList<>();
            for (JadwalJagaModel jadwalJaga : jadwalJagaList) {
                listDokterByPoli.add(jadwalJaga.getDokter());
            }
            //Membuat sebuah hashmap yang key-nya adalah doktermodel dan value-nya frekuensi dokter tersebut
            Map<DokterModel, Integer> map = new HashMap<>();

            //Looping untuk assign frekuensi tiap dokter
            for (DokterModel dokter : listDokterByPoli) {
                Integer val = map.get(dokter);
                //Setiap bertemu satu dokter maka jumlah akan ditambah 1
                map.put(dokter, val == null ? 1 : val + 1);
            }
            //Mencari nilai maksimum dari frekuensi yang ada di map
            Map.Entry<DokterModel, Integer> max = null;
            for (Map.Entry<DokterModel, Integer> e : map.entrySet()) {
                if (max == null || e.getValue() > max.getValue()) max = e;
            }
            //Nilai maksimum dari frekuensi yang ada di map
            int maxValue = max.getValue();

            //List untuk memasukkan dokter yang tersibuk
            List<DokterModel> listDokterTersibuk = new ArrayList<>();

            //mencari dokter dengan value yang sama seperti max lalu dimasukkan ke dalam list
            for (Map.Entry<DokterModel, Integer> s : map.entrySet()) {
                if (s.getValue() == maxValue) {
                    listDokterTersibuk.add(s.getKey());
                }
            }
            return maxValue == 0 ? null : listDokterTersibuk;

        } catch (NullPointerException e) {
            return null;
        }
    }
}
