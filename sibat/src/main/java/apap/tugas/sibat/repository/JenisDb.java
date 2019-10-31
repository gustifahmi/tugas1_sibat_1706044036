package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.JenisModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisDb extends JpaRepository<JenisModel, Long> {
    JenisModel findByIdJenis(Long idJenis);
}