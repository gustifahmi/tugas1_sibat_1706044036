package apap.tugas.sibat.service;

import apap.tugas.sibat.model.ObatModel;

import java.util.List;
import java.util.Optional;

public interface ObatService {
    void tambahObat(ObatModel obat);
    ObatModel ubahObat(ObatModel obat);
    Optional<ObatModel> detailObat(String nomorRegistrasi);
    List<ObatModel> obatLama(Long idGudang);
    List<ObatModel> filterObat(Long idGudang, Long idSupplier, Long idJenis);
}