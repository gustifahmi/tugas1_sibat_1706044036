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
    private long idGudang;

    @NotNull
    @Size(max = 20)
    @Column(name = "nama", nullable = false)
    private String namaGudang;

    @NotNull
    @Size(max = 50)
    @Column(name = "alamat", nullable = false)
    private String alamatGudang;

    @ManyToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ObatModel> listObat;

    public long getIdGudang() {
        return idGudang;
    }

    public void setIdGudang(long idGudang) {
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

    public List<ObatModel> getListObat() {
        return listObat;
    }

    public void setListObat(List<ObatModel> listObat) {
        this.listObat = listObat;
    }
}