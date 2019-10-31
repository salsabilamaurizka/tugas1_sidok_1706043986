package tugasapap.sidok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import tugasapap.sidok.model.PoliModel;
import tugasapap.sidok.service.PoliService;

import java.util.List;

@Controller
public class PoliController {
    @Qualifier(value = "poliServiceImpl")
    @Autowired
    private PoliService poliService;

    // URL mapping view
    @RequestMapping(path = "/poli", method = RequestMethod.GET)
    public String view(Model model) {
        List<PoliModel> allPoli = poliService.findAllPoli();
        model.addAttribute("listPoli", allPoli);
        // Return view template
        return "daftar-poli";
    }

    //URL mapping yang digunakan untuk mengakses halaman add poli
    @RequestMapping(value = "/poli/tambah", method = RequestMethod.GET)
    public String addPoliFormPage(Model model) {
        PoliModel newPoli = new PoliModel();
        model.addAttribute("poli", newPoli);
        return "form-add-poli";
    }

    //URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add poli
    @RequestMapping(value = "/poli/tambah", method = RequestMethod.POST)
    public RedirectView addPoliSubmit(@ModelAttribute PoliModel poli, RedirectAttributes attributes) {
        poliService.addPoli(poli);
        attributes.addFlashAttribute("success", true);
        attributes.addFlashAttribute("idPoli", poli.getIdPoli());
        return new RedirectView("/poli/view/" + poli.getIdPoli());
//        poliService.addPoli(poli);
//        model.addAttribute("poli", poli);
//        return "detail-poli";
    }

    //URL mapping yang digunakan untuk melihat detail dari suatu poli
    @RequestMapping(value = "/poli/view/{idPoli}", method = RequestMethod.GET)
    private String viewPoli(@PathVariable(value = "idPoli") Long idPoli, Model model) {
        PoliModel poli = poliService.getPoliById(idPoli).get();
        model.addAttribute("poli", poli);
        return "detail-poli";
    }

    //API yang digunakan untuk menuju halaman form change poli
    @RequestMapping(value = "/poli/update/{idPoli}", method = RequestMethod.GET)
    public String changePoliFormPage(@PathVariable Long idPoli, Model model) {
        PoliModel existingPoli = poliService.getPoliById(idPoli).get();
        model.addAttribute("poli", existingPoli);
        return "form-update-poli";
    }

    //API yang digunakan untuk submit form change poli
    @RequestMapping(value = "/poli/update/{idPoli}", method = RequestMethod.POST)
    public String changePoliFormSubmit(@PathVariable Long idPoli, @ModelAttribute PoliModel poli, Model model) {
        PoliModel newPoliData = poliService.changePoli(poli);
        model.addAttribute("poli", newPoliData);
        model.addAttribute("namaPoli", newPoliData.getNama());
        return "update-poli";
    }

    //URL mapping yang digunakan untuk delete poli
    @RequestMapping(value = "poli/delete/{idPoli}")
    public String delete(@PathVariable Long idPoli, Model model) {
        try {
            PoliModel poliData = poliService.getPoliById(idPoli).get();
            model.addAttribute("namaPoli", poliData.getNama());
            poliService.deletePoli(poliData);
            return "delete-poli";
        }
        catch (Exception e){
            return "error-page";
        }
    }
}