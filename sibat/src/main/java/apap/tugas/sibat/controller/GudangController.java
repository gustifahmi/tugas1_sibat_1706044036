package apap.tugas.sibat.controller;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.service.GudangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GudangController {

    @Qualifier("gudangServiceImpl")

    @Autowired
    private GudangService gudangService;

    @RequestMapping("/gudang")
    public String gudang(Model model){
        List<GudangModel> daftarGudang = gudangService.daftarGudang();
        model.addAttribute("daftarGudang", daftarGudang);
        return "gudang";
    }

    @RequestMapping(value = "/gudang/tambah", method = RequestMethod.GET)
    public String formTambahGudang(Model model) {
        GudangModel gudangBaru = new GudangModel();
        model.addAttribute("gudang", gudangBaru);
        return "form-tambah-gudang";
    }

    @RequestMapping(value = "/gudang/tambah", method = RequestMethod.POST)
    public String tambahGudang(@ModelAttribute GudangModel gudang, Model model) {
        gudangService.tambahGudang(gudang);
        model.addAttribute("gudang", gudang);
        return "tambah-gudang";
    }

    @RequestMapping(path = "/gudang/view", method = RequestMethod.GET)
    public String detailGudang(
            @RequestParam(value="idGudang") Long idGudang, Model model
    ) {
        GudangModel gudang = gudangService.getGudangByIdGudang(idGudang);
        model.addAttribute("gudang", gudang);
        return "detail-gudang";
    }

    @RequestMapping(value="gudang/ubah", method = RequestMethod.GET)
    public String formUbahGudang(@RequestParam(value="idGudang") Long idGudang, Model model) {
        GudangModel gudang = gudangService.getGudangByIdGudang(idGudang);
        model.addAttribute("gudang", gudang);
        return "form-ubah-gudang";
    }

    @RequestMapping(value="gudang/ubah", method = RequestMethod.POST)
    public String UbahGudang(@RequestParam(value="idGudang") Long idGudang, @ModelAttribute GudangModel gudang, Model model) {
        GudangModel gudangUpdate = gudangService.ubahGudang(gudang);
        model.addAttribute("gudang", gudangUpdate);
        return "ubah-gudang";
    }

    @RequestMapping("/gudang/hapus/{idGudang}")
    public String hapusGudang(
            @PathVariable("idGudang") Long idGudang, Model model) {
        GudangModel gudang = gudangService.getGudangByIdGudang(idGudang);
        model.addAttribute("gudang", gudang);
        gudangService.hapusGudang(idGudang);
        return "hapus-gudang";
    }
}