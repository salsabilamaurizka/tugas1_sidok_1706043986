package tugasapap.sidok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugasapap.sidok.model.SpesialisasiModel;
import tugasapap.sidok.service.SpesialisasiService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SpesialisasiController {

    @Autowired
    private SpesialisasiService spesialisasiService;

    //URL mapping view spesialisasi dan jumlah dokternya
    @RequestMapping(value = "/bonus", method = RequestMethod.GET)
    public String viewJumlahDokterBySpesialisasi(Model model) {
        spesialisasiService.setJumlahDokterSpesialisasi();
        List<SpesialisasiModel> spesialisasiList = spesialisasiService.getSpesialisasiList();
        model.addAttribute("listSpesialisasi", spesialisasiList);
        return "bonus";
    }
}
