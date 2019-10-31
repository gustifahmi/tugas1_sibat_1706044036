package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObatDb extends JpaRepository<ObatModel, Long> {
    ObatModel findByIdObat(Long idObat);
    ObatModel findByNomorRegistrasi(String nomorRegistasi);
    List<ObatModel> findByJenisModelIdJenis(Long JenisId);
}