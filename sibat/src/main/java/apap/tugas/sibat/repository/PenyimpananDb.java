package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.PenyimpananObat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenyimpananDb extends JpaRepository<PenyimpananObat, Long> {
    List<PenyimpananObat> findByGudangIdGudang(Long idGudang);
}