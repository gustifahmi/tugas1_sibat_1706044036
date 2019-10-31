package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDb extends JpaRepository<SupplierModel, Long> {
    SupplierModel findByIdSupplier(Long idSupplier);
}