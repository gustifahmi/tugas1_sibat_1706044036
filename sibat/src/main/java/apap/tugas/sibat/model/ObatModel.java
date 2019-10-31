package apap.tugas.sibat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

@Entity
@Table(name = "obat")
public class ObatModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idObat;

    @NotNull
    @Size(max = 15)
    @Column(name = "nomor_registrasi", nullable = false)
    private String nomorRegistrasi;

    @NotNull
    @Size(max = 20)
    @Column(name = "nama", nullable = false)
    private String namaObat;

    @NotNull
    @Size(max = 10)
    @Column(name = "jenis", nullable = false)
    private String jenis;

    @NotNull
    @Size(max = 10)
    @Column(name = "bentuk", nullable = false)
    private String bentuk;

    @Column(name = "tanggal_terbit")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date tanggalTerbit;

    @Column(name = "tanggal_kedaluwarsa")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date tanggalKedaluwarsa;

    @NotNull
    @Column(name = "harga", nullable = false)
    private BigInteger harga;

    @Size(max = 13)
    @Column(name = "kode_obat")
    private String kodeObat;

    @OneToMany(mappedBy = "obatDisimpan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenyimpananObat> penyimpanan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jenis_id", referencedColumnName = "idJenis")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisModel jenisModel;

    @OneToMany(mappedBy = "obatDipasok", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenyediaanObat> penyediaan;

    public Long getIdObat() {
        return idObat;
    }

    public String getNomorRegistrasi() {
        return nomorRegistrasi;
    }

    public void setNomorRegistrasi(String nomorRegistrasi) {
        this.nomorRegistrasi = nomorRegistrasi;
    }

    public void generateKode() {
        String kodeObat = "";

        if(jenis.equalsIgnoreCase("generik"))
            kodeObat += "1";
        else if(jenis.equalsIgnoreCase("paten"))
            kodeObat += "2";

        if(bentuk.equalsIgnoreCase("cairan"))
            kodeObat += "01";
        else if(bentuk.equalsIgnoreCase("kapsul"))
            kodeObat += "02";
        else if(bentuk.equalsIgnoreCase("tablet"))
            kodeObat += "03";

        kodeObat = kodeObat + ((int) tanggalTerbit.getYear() + 1900);
        kodeObat = kodeObat + ((int) tanggalKedaluwarsa.getYear() + 1900);
        Random random = new Random();
        for(int i = 0; i < 2; i++) {
            kodeObat += (char) (random.nextInt(26) + 65);
        }
        this.kodeObat = kodeObat;
    }

    public String getKodeObat() { return kodeObat; }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat.toUpperCase();
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getBentuk() {
        return bentuk;
    }

    public void setBentuk(String bentuk) {
        this.bentuk = bentuk;
    }

    public Date getTanggalTerbit() {
        return tanggalTerbit;
    }

    public void setTanggalTerbit(Date tanggalTerbit) {
        this.tanggalTerbit = tanggalTerbit;
    }

    public Date getTanggalKedaluwarsa() {
        return tanggalKedaluwarsa;
    }

    public void generateTanggalKedaluwarsa() {
        Calendar c = Calendar.getInstance();
        c.setTime(tanggalTerbit);
        c.add(Calendar.YEAR, 5);
        tanggalKedaluwarsa = c.getTime();
    }

    public BigInteger getHarga() {
        return harga;
    }

    public void setHarga(BigInteger harga) {
        this.harga = harga;
    }

    public List<PenyimpananObat> getPenyimpanan() {
        return penyimpanan;
    }

    public void setPenyimpanan(List<PenyimpananObat> penyimpanan) {
        this.penyimpanan = penyimpanan;
    }

    public List<PenyediaanObat> getPenyediaan() {
        return penyediaan;
    }

    public void setPenyediaan(List<PenyediaanObat> penyediaan) {
        this.penyediaan = penyediaan;
    }

    public JenisModel getJenisModel() {
        return jenisModel;
    }

    public void setJenisModel(JenisModel jenisModel) {
        this.jenisModel = jenisModel;
    }
}