package tugasapap.sidok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tugasapap.sidok.model.DokterModel;
import tugasapap.sidok.model.SpesialisasiModel;
import tugasapap.sidok.service.DokterService;
import tugasapap.sidok.service.SpesialisasiService;

import java.util.List;

@Controller
public class DokterController {
    @Autowired
    private SpesialisasiService spesialisasiService;

    @Qualifier(value="dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    // URL mapping view
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String view(Model model){
        List<DokterModel> allDokter = dokterService.findAllDokter();
        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("listDokter", allDokter);
        // Return view template
        return "beranda";
    }

    //URL mapping yang digunakan untuk mengakses halaman add restoran
    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.GET)
    public String addDokterFormPage(Model model) {
//        String data = spesialisasiService.get
        DokterModel newDokter = new DokterModel();
        model.addAttribute("dokter", newDokter);
        return "form-add-dokter";
    }

    //URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add restoran
    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST)
    public String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
        String nip = dokter.generateNIPDokter();
        dokter.setNip(nip);
//        List<SpesialisasiModel> spesialisasiNow = spesialisasiService.findAllSpesialisasi();

        dokterService.addDokter(dokter);
//        model.addAttribute("listSpesialisasi", spesialisasiNow);
        model.addAttribute("nama", dokter.getNama());
        model.addAttribute("nip", dokter.getNip());

        return "add-dokter";
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


    //URL mapping yang digunakan untuk delete restoran
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
}
