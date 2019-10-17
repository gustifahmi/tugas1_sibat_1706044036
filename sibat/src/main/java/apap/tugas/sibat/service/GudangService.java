package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.model.ObatModel;

import java.util.List;
import java.util.Optional;

public interface GudangService {
    List<GudangModel> daftarGudang();
    void tambahGudang(GudangModel gudang);
    GudangModel ubahGudang(GudangModel gudang);
    void hapusGudang(Long idGudang);
    Optional<GudangModel> detailGudang(Long idGudang);
}
