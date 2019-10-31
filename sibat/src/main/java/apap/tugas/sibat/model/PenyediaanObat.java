package apap.tugas.sibat.model;

import javax.persistence.*;

@Entity
@Table(name = "penyediaan")
public class PenyediaanObat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPenyediaan;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private SupplierModel supplier;

    @ManyToOne
    @JoinColumn(name = "obatId")
    private ObatModel obatDipasok;

    public Long getIdPenyediaan() {
        return idPenyediaan;
    }

    public void setIdPenyediaan(Long idPenyediaan) {
        this.idPenyediaan = idPenyediaan;
    }

    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
    }

    public ObatModel getObatDipasok() {
        return obatDipasok;
    }

    public void setObatDipasok(ObatModel obatDipasok) {
        this.obatDipasok = obatDipasok;
    }
}