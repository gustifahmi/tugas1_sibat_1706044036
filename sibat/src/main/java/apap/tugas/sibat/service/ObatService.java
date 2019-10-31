package apap.tugas.sibat.service;

import apap.tugas.sibat.model.ObatModel;

import java.util.List;

public interface ObatService {
    void tambahObat(ObatModel obat);
    ObatModel ubahObat(ObatModel obat);
    List<ObatModel> daftarObat();
    void hapusObat(Long idObat);
    ObatModel getObatByNomor(String nomorRegistrasi);
    ObatModel getObatByIdObat(Long idObat);
    List<ObatModel> obatLama(Long idGudang);
    List<ObatModel> filterObat(Long idGudang, Long idSupplier, Long idJenis);

}