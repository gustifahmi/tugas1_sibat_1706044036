package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.PenyediaanObat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenyediaanDb extends JpaRepository<PenyediaanObat, Long> {
    List<PenyediaanObat> findBySupplierIdSupplier(Long idSupplier);
    List<PenyediaanObat> findByObatDipasokIdObat(Long idObat);
}
