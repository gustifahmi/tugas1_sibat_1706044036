package apap.tugas.sibat.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jenis")
public class JenisModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idJenis;

    @NotNull
    @Size(max = 10)
    @Column(name = "nama", nullable = false)
    private String namaJenis;

    @NotNull
    @Size(max = 50)
    @Column(name = "deskripsi")
    private String deskripsi;

    @OneToMany(mappedBy = "jenisModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ObatModel> listObat;

    public Long getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(Long idJenis) {
        this.idJenis = idJenis;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public List<ObatModel> getListObat() {
        return listObat;
    }

    public void setListObat(List<ObatModel> listObat) {
        this.listObat = listObat;
    }

    public void addObat(ObatModel obat) {
        this.listObat.add(obat);
    }
}
