package apap.tugas.sibat.service;

import apap.tugas.sibat.model.*;
import apap.tugas.sibat.repository.ObatDb;
import apap.tugas.sibat.repository.PenyediaanDb;
import apap.tugas.sibat.repository.PenyimpananDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ObatServiceImpl implements ObatService {
    @Autowired
    private ObatDb obatDb;

    @Autowired
    private PenyimpananDb penyimpananDb;

    @Autowired
    private PenyediaanDb penyediaanDb;

    @Override
    public void tambahObat(ObatModel obat) {
        obatDb.save(obat);
    }

    @Override
    public ObatModel ubahObat(ObatModel obatBaru) {
        ObatModel obatAwal = obatDb.findById(obatBaru.getIdObat()).get();
        try{
            obatAwal.setNamaObat(obatBaru.getNamaObat());
            obatAwal.setBentuk(obatBaru.getBentuk());
            obatAwal.setTanggalTerbit(obatBaru.getTanggalTerbit());
            obatAwal.setHarga(obatBaru.getHarga());
            obatDb.save(obatAwal);
            return obatAwal;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public List<ObatModel> daftarObat() { return obatDb.findAll();}

    @Override
    public void hapusObat(Long idObat) {
        ObatModel obat = getObatByIdObat(idObat);
        obatDb.delete(obat);
    }

    @Override
    public ObatModel getObatByNomor(String nomorRegistrasi) {
        return obatDb.findByNomorRegistrasi(nomorRegistrasi);
    }

    @Override
    public ObatModel getObatByIdObat(Long idObat) {
        return obatDb.findByIdObat(idObat);
    }

    @Override
    public List<ObatModel> obatLama(Long idGudang) {
        List<ObatModel> daftarObat = new ArrayList<>();
        List<PenyimpananObat> daftarPenyimpanan = penyimpananDb.findByGudangIdGudang(idGudang);
        if(!daftarPenyimpanan.isEmpty()) {
            for(PenyimpananObat penyimpanan : daftarPenyimpanan) {
                daftarObat.add(penyimpanan.getObatDisimpan());
            }
        }
        List<ObatModel> obatLama = new ArrayList<>();
        Date today = java.sql.Date.valueOf(LocalDate.now());
        for (ObatModel obat : daftarObat) {
            if(obat.getTanggalKedaluwarsa().before(today)) {
                obatLama.add(obat);
            }
        }
        if(!obatLama.isEmpty())
            return obatLama;
        else
            return null;
    }

    @Override
    public List<ObatModel> filterObat(Long idGudang, Long idSupplier, Long idJenis) {
        List<ObatModel> daftarObat = new ArrayList<>();
        List<ObatModel> daftarObatSementara = new ArrayList<>();
        boolean adaParam = false;
        if (!idGudang.equals(null)) {
            List<PenyimpananObat> daftarPenyimpanan = penyimpananDb.findByGudangIdGudang(idGudang);
            if (!daftarPenyimpanan.isEmpty()) {
                for (PenyimpananObat penyimpanan : daftarPenyimpanan) {
                    daftarObat.add(penyimpanan.getObatDisimpan());
                }
            }
            adaParam = true;    //Menandakan punya param sebelumnya/udah lewat if sebelumnya
        }
        if (!idSupplier.equals(null)) {
            if (!adaParam) {
                List<PenyediaanObat> daftarPenyediaan = penyediaanDb.findBySupplierIdSupplier(idSupplier);
                if (!daftarPenyediaan.isEmpty()) {
                    for (PenyediaanObat penyediaan : daftarPenyediaan) {
                        daftarObat.add(penyediaan.getObatDipasok());
                    }
                }
            } else {
                for (ObatModel obat : daftarObat) {
                    List<PenyediaanObat> daftarPenyediaan = penyediaanDb.findByObatDipasokIdObat(obat.getIdObat());
                    if (!daftarPenyediaan.isEmpty()) {
                        for (PenyediaanObat penyediaan : daftarPenyediaan) {
                            if (penyediaan.getSupplier().getIdSupplier().equals(idSupplier)) {
                                daftarObatSementara.add(obat);
                                break;
                            }
                        }
                    }
                }
                daftarObat = daftarObatSementara;
            }
            adaParam = true;
        }
        if (!idJenis.equals(null)) {
            if (!adaParam)
                daftarObat = obatDb.findByJenisModelIdJenis(idJenis);
            else {
                daftarObatSementara.clear();
                for (ObatModel obat : daftarObat) {
                    JenisModel jenis = obat.getJenisModel();
                    if (jenis.getIdJenis().equals(idJenis))
                        daftarObatSementara.add(obat);
                }
                daftarObat = daftarObatSementara;
            }
        }
        return daftarObat;
    }
}