package apap.tugas.sibat.controller;

import apap.tugas.sibat.model.JenisModel;
import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.service.JenisService;
import apap.tugas.sibat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ObatController {

    @Qualifier("obatServiceImpl")

    @Autowired
    private ObatService obatService;

    @Autowired
    private JenisService jenisService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/")
    public String home(Model model){
        List<ObatModel> daftarObat = obatService.daftarObat();
        model.addAttribute("daftarObat", daftarObat);
        return "home";
    }

    @RequestMapping(value = "/obat/tambah", method = RequestMethod.GET)
    public String formTambahObat(Model model) {
        ObatModel obatBaru = new ObatModel();
        model.addAttribute("obat", obatBaru);
        return "form-tambah-obat";
    }

    @RequestMapping(value = "/obat/tambah", method = RequestMethod.POST)
    public String tambahObat(@ModelAttribute ObatModel obat, Model model) {
        obat.generateTanggalKedaluwarsa();
        obat.generateKode();
        String jenis = obat.getJenis();
        if(jenis.equalsIgnoreCase("generik")) {
            JenisModel generik = jenisService.cariJenis(new Long(1));
            obat.setJenisModel(generik);
            generik.addObat(obat);
        }
        else {
            JenisModel paten = jenisService.cariJenis(new Long(2));
            obat.setJenisModel(paten);
            paten.addObat(obat);
        }
        obatService.tambahObat(obat);
        model.addAttribute("obat", obat);
        return "tambah-obat";
    }

    @RequestMapping(path = "/obat/view", method = RequestMethod.GET)
    public String detailObat(
            @RequestParam(value="noReg") String nomorRegistrasi, Model model
    ) {
        ObatModel obat = obatService.getObatByNomor(nomorRegistrasi);
        model.addAttribute("obat", obat);
        return "detail-obat";
    }

    @RequestMapping(value="obat/ubah", method = RequestMethod.GET)
    public String formUbahObat(@RequestParam(value="id") Long idObat, Model model) {
        ObatModel obat = obatService.getObatByIdObat(idObat);
        model.addAttribute("obat", obat);
        return "form-ubah-obat";
    }

    @RequestMapping(value="obat/ubah", method = RequestMethod.POST)
    public String UbahObat(@RequestParam(value="id") Long idObat, @ModelAttribute ObatModel obat, Model model) {
        obat.generateTanggalKedaluwarsa();
        obat.generateKode();
        ObatModel obatUpdate = obatService.ubahObat(obat);
        model.addAttribute("obat", obatUpdate);
        return "ubah-obat";
    }

    @RequestMapping("/obat/hapus/{idObat}")
    public String hapusObat(
            @PathVariable("idObat") Long idObat, Model model) {
        ObatModel obat = obatService.getObatByIdObat(idObat);
        model.addAttribute("obat", obat);
        obatService.hapusObat(idObat);
        return "home";
    }
}