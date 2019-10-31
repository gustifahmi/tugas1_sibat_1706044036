package apap.tugas.sibat.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "gudang")
public class GudangModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGudang;

    @NotNull
    @Size(max = 20)
    @Column(name = "nama", nullable = false)
    private String namaGudang;

    @NotNull
    @Size(max = 50)
    @Column(name = "alamat", nullable = false)
    private String alamatGudang;

    @OneToMany(mappedBy = "gudang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenyimpananObat> penyimpanan;

    public Long getIdGudang() {
        return idGudang;
    }

    public void setIdGudang(Long idGudang) {
        this.idGudang = idGudang;
    }

    public String getNamaGudang() {
        return namaGudang;
    }

    public void setNamaGudang(String namaGudang) {
        this.namaGudang = namaGudang;
    }

    public String getAlamatGudang() {
        return alamatGudang;
    }

    public void setAlamatGudang(String alamatGudang) {
        this.alamatGudang = alamatGudang;
    }

    public List<PenyimpananObat> getPenyimpanan() {
        return penyimpanan;
    }

    public void setPenyimpanan(List<PenyimpananObat> penyimpanan) {
        this.penyimpanan = penyimpanan;
    }
}