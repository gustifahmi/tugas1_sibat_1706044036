package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObatDb extends JpaRepository<ObatModel, Long> {
    Optional<ObatModel> findByNomorRegistrasi(String nomorRegistasi);
    List<ObatModel> findByGudangIdGudang(Long gudangId);
    List<ObatModel> findBySupplierIdSupplier(Long supplierId);
    List<ObatModel> findByJenisIdJenis(Long jenisId);
}