package apap.tugas.sibat.model;

import javax.persistence.*;

@Entity
@Table(name = "penyimpanan")
public class PenyimpananObat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPenyimpanan;

    @ManyToOne
    @JoinColumn(name = "gudangId")
    private GudangModel gudang;

    @ManyToOne
    @JoinColumn(name = "obatId")
    private ObatModel obatDisimpan;

    public Long getIdPenyimpanan() {
        return idPenyimpanan;
    }

    public void setIdPenyimpanan(Long idPenyimpanan) {
        this.idPenyimpanan = idPenyimpanan;
    }

    public GudangModel getGudang() {
        return gudang;
    }

    public void setGudang(GudangModel gudang) {
        this.gudang = gudang;
    }

    public ObatModel getObatDisimpan() {
        return obatDisimpan;
    }

    public void setObatDisimpan(ObatModel obatDisimpan) {
        this.obatDisimpan = obatDisimpan;
    }
}