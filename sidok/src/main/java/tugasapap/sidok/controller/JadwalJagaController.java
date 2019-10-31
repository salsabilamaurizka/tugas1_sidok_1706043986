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

    //URL mapping yang digunakan untuk mengakses halaman add jadwal jaga
    @RequestMapping(value = "jadwal/tambah/{nip}", method = RequestMethod.GET)
    public String jadwalJagaFormPage(@PathVariable String nip, Model model) {
        JadwalJagaModel jadwalJagaModel = new JadwalJagaModel();
        model.addAttribute("jadwalJaga", jadwalJagaModel);

        DokterModel dokterModel = dokterService.getDokterByNip(nip).get();
        model.addAttribute("dokter", dokterModel);

        List<PoliModel> poliList = poliService.getPoliList();
        model.addAttribute("poliList", poliList);

        List<JadwalJagaModel> JadwalJagaModels = jadwalJagaService.getJadwalJagaByDokter(dokterModel);
        model.addAttribute("jadwalJagaList", JadwalJagaModels);

        return "form-add-jadwal-jaga";
    }

    //URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add jadwal jaga
    @RequestMapping(value = "jadwal/tambah/{nip}", method = RequestMethod.POST)
    public String tambahJadwalJagaSubmit(@PathVariable String nip, @ModelAttribute JadwalJagaModel jadwalJaga, Model model) {
        DokterModel dokter = dokterService.getDokterByNip(nip).get();
        jadwalJaga.setDokter(dokter);
        jadwalJagaService.addJadwalJaga(jadwalJaga);
        model.addAttribute("jadwalJaga",jadwalJaga);
        model.addAttribute("dokter",dokter);
        return "add-jadwal-jaga";
    }

    // URL mapping view poli
    @RequestMapping(path = "/poli/dokter/{idPoli}", method = RequestMethod.GET)
    public String viewDokterPoli(@PathVariable Long idPoli, Model model) {
        PoliModel poli = poliService.getPoliById(idPoli).get();
        List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getJadwalJagaByPoli(poli);

        model.addAttribute("listJadwalJagaPoli", listJadwalJaga);
        model.addAttribute("poli", poli);

        // Return view template
        return "daftar-dokter-poli";
    }

}
