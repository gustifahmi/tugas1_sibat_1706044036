package apap.tugas.sibat.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jenis")
public class JenisModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idJenis;

    @NotNull
    @Size(max = 10)
    @Column(name = "nama", nullable = false)
    private String namaJenis;

    @NotNull
    @Column(name = "deskripsi", nullable = false)
    private TextField deskripsi;

    @OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ObatModel> listObat;

    public long getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(long idJenis) {
        this.idJenis = idJenis;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
    }

    public TextField getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(TextField deskripsi) {
        this.deskripsi = deskripsi;
    }

    public List<ObatModel> getListObat() {
        return listObat;
    }

    public void setListObat(List<ObatModel> listObat) {
        this.listObat = listObat;
    }
}
