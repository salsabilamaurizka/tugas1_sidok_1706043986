package tugasapap.sidok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tugasapap.sidok.model.DokterModel;
import tugasapap.sidok.model.JadwalJagaModel;
import tugasapap.sidok.model.PoliModel;
import tugasapap.sidok.model.SpesialisasiModel;
import tugasapap.sidok.service.DokterService;
import tugasapap.sidok.service.JadwalJagaService;
import tugasapap.sidok.service.PoliService;
import tugasapap.sidok.service.SpesialisasiService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DokterController {
    @Autowired
    private SpesialisasiService spesialisasiService;

    @Autowired
    private PoliService poliService;

    @Autowired
    private JadwalJagaService jadwalJagaService;

    @Qualifier(value="dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    // URL mapping view
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String view(Model model){
        List<DokterModel> allDokter = dokterService.findAllDokter();
        model.addAttribute("listDokter", allDokter);
        return "beranda";
    }

    //URL mapping yang digunakan untuk mengakses halaman add restoran
    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.GET)
    public String addDokterFormPage(Model model) {
        DokterModel newDokter = new DokterModel();
        List<SpesialisasiModel> spesialisasi = spesialisasiService.getSpesialisasiList();

        ArrayList<SpesialisasiModel> listSpesialisasi = new ArrayList<SpesialisasiModel>();
        listSpesialisasi.add(new SpesialisasiModel());
        newDokter.setListSpesialisasi(listSpesialisasi);
        model.addAttribute("dokter", newDokter);
        model.addAttribute("listSpesialisasi", spesialisasi);
        return "form-add-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST)
    public String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
        String nip = dokterService.generateNIPDokter(dokter);
        dokter.setNip(nip);

        dokterService.addDokter(dokter);
//        model.addAttribute("listSpesialisasi", spesialisasiNow);
        model.addAttribute("nama", dokter.getNama());
        model.addAttribute("nip", dokter.getNip());

        return "add-dokter";
    }

    @RequestMapping(value="/dokter/tambah", method = RequestMethod.POST, params= {"addRow"})
    public String addRowDokter(@ModelAttribute DokterModel dokterModel, BindingResult bindingResult, Model model) {
        if (dokterModel.getListSpesialisasi() == null) {
            dokterModel.setListSpesialisasi(new ArrayList<SpesialisasiModel>());
        }
        dokterModel.getListSpesialisasi().add(new SpesialisasiModel());
        model.addAttribute("dokter", dokterModel);

        List<SpesialisasiModel> spesialisasiList = spesialisasiService.getSpesialisasiList();
        model.addAttribute("listSpesialisasi", spesialisasiList);

        return "form-add-dokter";
    }

    // URL mapping view
    @RequestMapping(path = "/dokter", method = RequestMethod.GET)
    public String dokterView(
            @RequestParam(value = "nikDokter") String nik, Model model){
        DokterModel dokter = dokterService.getDokterByNik(nik).get();
        model.addAttribute("dokter",dokter);
            return "detail-dokter";
    }

    //API yang digunakan untuk menuju halaman form change restoran
    @RequestMapping(value = "/dokter/update/{idDokter}", method = RequestMethod.GET)
    public String changeDokterFormPage(@PathVariable Long idDokter, Model model) {
        //Mengambil existing data restoran
        DokterModel existingDokter = dokterService.getDokterByIdDokter(idDokter).get();
        model.addAttribute("dokter", existingDokter);
        return "form-update-dokter";
    }

    //API yang digunakan untuk submit form change restoran
    @RequestMapping(value = "/dokter/update/{idDokter}", method = RequestMethod.POST)
    public String changeDokterFormSubmit(@PathVariable Long idDokter, @ModelAttribute DokterModel dokter, Model model) {
        DokterModel newDokterData = dokterService.changeDokter(dokter);
        model.addAttribute("dokter", newDokterData);
        model.addAttribute("nama", dokter.getNama());
        return "update-dokter";
    }

//    @RequestMapping(value = "/dokter/update/{idDokter}", method = RequestMethod.POST)
//    public RedirectView changeDokterFormSubmit(@PathVariable Long idDokter, @ModelAttribute DokterModel dokter, Model model, RedirectAttributes attributes) {
//        DokterModel newDokterData = dokterService.changeDokter(dokter);
////        model.addAttribute("dokter", newDokterData);
////        model.addAttribute("nama", dokter.getNama());
//        attributes.addAttribute("nikDokter", newDokterData.getNik());
//        return new RedirectView("/dokter");
//    }


    //URL mapping yang digunakan untuk delete dokter
    @RequestMapping(value = "dokter/delete/{idDokter}")
    public String delete(@PathVariable Long idDokter, Model model) {
        try {
            DokterModel dokterData = dokterService.getDokterByIdDokter(idDokter).get();
            model.addAttribute("namaDokter", dokterData.getNama());
            dokterService.deleteDokter(dokterData);
            return "delete-dokter";
        }
        catch (Exception e){
            return "error-page";
        }
    }

    @RequestMapping(value = "/cari", method = RequestMethod.GET)
    public String cariDokterByPoliAndSpesialisasi(Model model) {

        List<PoliModel> poliList = poliService.getPoliList();
        model.addAttribute("poliList", poliList);

        List <SpesialisasiModel> spesialisasiList = spesialisasiService.getSpesialisasiList();
        model.addAttribute("spesialisasiList", spesialisasiList);

        return "cari-dokter-spesialis-pada-Poli";
    }

    @RequestMapping(value="/cari", method=RequestMethod.GET, params= {"idSpesialisasi","idPoli"})
    public String cariDokterByPoliSpesialisasi(
            @RequestParam(value="idPoli") Long idPoli,
            @RequestParam(value="idSpesialisasi") Long idSpesialisasi, Model model) {

        List<PoliModel> poliList = poliService.findAllPoli();
        model.addAttribute("poliList", poliList);

        List<SpesialisasiModel> spesialisasiList = spesialisasiService.getSpesialisasiList();
        model.addAttribute("spesialisasiList", spesialisasiList);

        PoliModel poli = poliService.getPoliById(idPoli).get();
        SpesialisasiModel spesialisasi = spesialisasiService.getSpesialisasiById(idSpesialisasi).get();

        List<JadwalJagaModel> jadwalJagaByPoliList = jadwalJagaService.getJadwalJagaByPoli(poli);
        List<DokterModel> listDokter = new ArrayList<DokterModel>();

        for (JadwalJagaModel jadwalJaga : jadwalJagaByPoliList) {
            int spesialisasiLength = jadwalJaga.getDokter().getListSpesialisasi().size();
            for (int i = 0; i < spesialisasiLength; i++) {
                if (spesialisasi == jadwalJaga.getDokter().getListSpesialisasi().get(i)) {
                    listDokter.add(jadwalJaga.getDokter());
                }
            }
        }
        model.addAttribute("listDokter", listDokter);
        return "cari-dokter-spesialis-pada-Poli";
    }

    @RequestMapping(value = "/cari-yang-terbanyak", method = RequestMethod.GET)
    public String cariDokterTerbanyakPoli(Model model) {

        List<PoliModel> poliList = poliService.getPoliList();
        model.addAttribute("poliList", poliList);

        return "cari-dokter-paling-banyak-bertugas";
    }

    @RequestMapping(value="/cari", method=RequestMethod.GET, params= {"idPoli"})
    public String cariDokterTerbanyakDiPoli(
            @RequestParam(value="idPoli") Long idPoli, Model model) {

        List<PoliModel> poliList = poliService.getPoliList();
        model.addAttribute("poliList", poliList);

        if(idPoli == 0) {
            DokterModel dokter = new DokterModel();
            PoliModel poli = new PoliModel();
            model.addAttribute("poli", poli);
            model.addAttribute("dokter", dokter);
            model.addAttribute("data", "kosong");
            return "cari-dokter-paling-banyak-bertugas";
        }
        else{
            DokterModel hasilDokter = jadwalJagaService.findMostDokter(idPoli);
            Optional<PoliModel> poli = poliService.getPoliById(idPoli);
            model.addAttribute("poli", poli);
            model.addAttribute("dokter", hasilDokter);
            return "cari-dokter-paling-banyak-bertugas";
        }
    }
}
