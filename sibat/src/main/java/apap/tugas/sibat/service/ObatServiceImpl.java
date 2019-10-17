package apap.tugas.sibat.service;

import apap.tugas.sibat.model.JenisModel;
import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.model.SupplierModel;
import apap.tugas.sibat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObatServiceImpl implements ObatService {
    @Autowired
    private ObatDb obatDb;

    @Override
    public void tambahObat(ObatModel obat) {
        obatDb.save(obat);
    }

    @Override
    public ObatModel ubahObat(ObatModel obatBaru) {
        ObatModel obatAwal = obatDb.findById(obatBaru.getIdObat()).get();
        try{
            obatAwal.setNamaObat(obatBaru.getNamaObat());
            obatAwal.setNomorRegistrasi(obatBaru.getNomorRegistrasi());
            obatAwal.setJenis(obatBaru.getJenis());
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
    public Optional<ObatModel> detailObat(String nomorRegistrasi) {
        return obatDb.findByNomorRegistrasi(nomorRegistrasi);
    }

    @Override
    public List<ObatModel> obatLama(Long idGudang) {
        List<ObatModel> daftarObat = obatDb.findByGudangIdGudang(idGudang);
        List<ObatModel> obatLama = new ArrayList<>();
        for (ObatModel obat : daftarObat) {
            if(obat.getTanggalKedaluwarsa().before(LocalDate.now())) {
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
            daftarObat = obatDb.findByGudangIdGudang(idGudang);
            adaParam = true;    //Menandakan punya param sebelumnya/udah lewat if sebelumnya
        }
        if (!idSupplier.equals(null)) {
            adaParam = true;
            if(!adaParam)
                daftarObat = obatDb.findBySupplierIdSupplier(idSupplier);
            else{
                for (ObatModel obat : daftarObat) {
                    List<SupplierModel> daftarSupplier = obat.getListSupplier();
                    for (SupplierModel supplier : daftarSupplier) {
                        if (supplier.getIdSupplier() == idSupplier) {
                            daftarObatSementara.add(obat);
                            break;
                        }
                    }
                }
                daftarObat = daftarObatSementara;
                daftarObatSementara = new ArrayList<>();
            }
        }
        if (!idJenis.equals(null)) {
            if (!adaParam)
                daftarObat = obatDb.findByJenisIdJenis(idJenis);
            else{
                for (ObatModel obat : daftarObat) {
                    JenisModel jenis = obat.getJenisModel();
                    if (jenis.getIdJenis() == idJenis)
                        daftarObatSementara.add(obat);
                }
                daftarObat = daftarObatSementara;
            }
        }
        return daftarObat;
    }
}