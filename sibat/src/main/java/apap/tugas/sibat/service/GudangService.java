package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangModel;

import java.util.List;

public interface GudangService {
    List<GudangModel> daftarGudang();
    void tambahGudang(GudangModel gudang);
    GudangModel ubahGudang(GudangModel gudang);
    void hapusGudang(Long idGudang);
    GudangModel getGudangByIdGudang(Long idGudang);
}
