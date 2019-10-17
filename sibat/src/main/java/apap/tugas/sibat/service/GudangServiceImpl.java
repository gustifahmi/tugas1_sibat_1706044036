package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    private GudangDb gudangDb;

    @Override
    public List<GudangModel> daftarGudang() {
        return gudangDb.findAll();
    }

    @Override
    public void tambahGudang(GudangModel gudang) {
        gudangDb.save(gudang);
    }

    @Override
    public GudangModel ubahGudang(GudangModel gudangBaru) {
        GudangModel gudangAwal = gudangDb.findById(gudangBaru.getIdGudang()).get();
        try{
            gudangAwal.setNamaGudang(gudangBaru.getNamaGudang());
            gudangAwal.setAlamatGudang(gudangBaru.getAlamatGudang());
            gudangDb.save(gudangAwal);
            return gudangAwal;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void hapusGudang(Long idGudang) {
        GudangModel gudang = detailGudang(idGudang).get();
        gudangDb.delete(gudang);
    }

    @Override
    public Optional<GudangModel> detailGudang(Long idGudang) {
        return gudangDb.findByIdGudang(idGudang);
    }
}