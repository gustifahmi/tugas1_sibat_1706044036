package apap.tugas.sibat.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "supplier")
public class SupplierModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSupplier;

    @NotNull
    @Size(max = 20)
    @Column(name = "nama", nullable = false)
    private String namaSupplier;

    @NotNull
    @Size(max = 50)
    @Column(name = "alamat")
    private String alamatSupplier;

    @NotNull
    @Size(max = 12)
    @Column(name = "nomor_telepon")
    private String nomorTelepon;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenyediaanObat> penyediaan;

    public Long getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Long idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public String getAlamatSupplier() {
        return alamatSupplier;
    }

    public void setAlamatSupplier(String alamatSupplier) {
        this.alamatSupplier = alamatSupplier;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public List<PenyediaanObat> getPenyediaan() {
        return penyediaan;
    }

    public void setPenyediaan(List<PenyediaanObat> penyediaan) {
        this.penyediaan = penyediaan;
    }
}