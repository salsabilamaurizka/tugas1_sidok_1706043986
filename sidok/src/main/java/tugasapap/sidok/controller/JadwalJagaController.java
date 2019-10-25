package tugasapap.sidok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugasapap.sidok.model.DokterModel;
import tugasapap.sidok.model.JadwalJagaModel;
import tugasapap.sidok.model.PoliModel;
import tugasapap.sidok.service.DokterService;
import tugasapap.sidok.service.JadwalJagaService;
import tugasapap.sidok.service.PoliService;

import java.util.List;

@Controller
public class JadwalJagaController {
    @Autowired
    DokterService dokterService;

    @Autowired
    PoliService poliService;

    @Qualifier("jadwalJagaServiceImpl")
    @Autowired
    JadwalJagaService jadwalJagaService;

    //URL mapping yang digunakan untuk mengakses halaman add jadwaljaga
    @RequestMapping(value = "/jadwal/tambah/{nip}", method = RequestMethod.GET)
    public String addJadwalJagaFormPage(@PathVariable String nip, Model model) {
        DokterModel dokterNow  = dokterService.getDokterByNip(nip).get();
        List<PoliModel> poliNow = poliService.findAllPoli();
        List<JadwalJagaModel> listJadwal = jadwalJagaService.findAllJadwalJagaByIdDokter();
        model.addAttribute("Dokter", dokterNow);
        model.addAttribute("listPoli", poliNow);
        model.addAttribute("jadwaljaga", listJadwal);
        return "tambah-jadwal-jaga-poli";
    }

    //URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add jadwaljaga
    @RequestMapping(value = "/jadwal/tambah/{nip}", method = RequestMethod.POST)
    public String addJadwalJagaSubmit(@PathVariable String nip,
                                      @ModelAttribute("idPoli") Long idPoli,
                                      @ModelAttribute("hari") String hari,
                                      Model model) {
        DokterModel dokterNow  = dokterService.getDokterByNip(nip).get();
        PoliModel poliNow = poliService.getPoliById(idPoli).get();
        JadwalJagaModel newJadwalJaga = new JadwalJagaModel();
        newJadwalJaga.setPoli(poliNow);
        newJadwalJaga.setDokter(dokterNow);
        newJadwalJaga.setHari(hari);
        jadwalJagaService.addJadwalJaga(newJadwalJaga);
        model.addAttribute("Dokter", dokterNow);
        model.addAttribute("listPoli", poliNow);
        model.addAttribute("hari", hari);

        return "add-jadwal-jaga";
    }

}
