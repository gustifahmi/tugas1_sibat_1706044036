package apap.tugas.sibat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "obat")
public class ObatModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idObat;

    @NotNull
    @Size(max = 15)
    @Column(name = "nomorRegistrasi", nullable = false)
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

    @NotNull
    @Column(name = "tanggalTerbit", nullable = false)
    private Calendar tanggalTerbit;

    @NotNull
    @Column(name = "tanggalKedaluwarsa", nullable = false)
    private Calendar tanggalKedaluwarsa;

    @NotNull
    @Column(name = "harga", nullable = false)
    private BigInteger harga;

    @ManyToMany(mappedBy = "gudang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangModel> listGudang;

    @ManyToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SupplierModel> listSupplier;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jenisId", referencedColumnName = "idJenis", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisModel jenisModel;

    public long getIdObat() {
        return idObat;
    }

    public void setIdObat(long idObat) {
        this.idObat = idObat;
    }

    public String getNomorRegistrasi() {
        return nomorRegistrasi;
    }

    public void setNomorRegistrasi(String nomorRegistrasi) {
        this.nomorRegistrasi = nomorRegistrasi;
    }

    public void generateNomorRegistrasi() {
        nomorRegistrasi = "";

        if(jenis.equalsIgnoreCase("generik"))
            nomorRegistrasi += "1";
        else if(jenis.equalsIgnoreCase("paten"))
            nomorRegistrasi += "2";

        if(bentuk.equalsIgnoreCase("cairan"))
            nomorRegistrasi += "01";
        else if(bentuk.equalsIgnoreCase("kapsul"))
            nomorRegistrasi += "02";
        else if(bentuk.equalsIgnoreCase("tablet"))
            nomorRegistrasi += "03";

        nomorRegistrasi = nomorRegistrasi + (tanggalTerbit.get(Calendar.YEAR));
        nomorRegistrasi = nomorRegistrasi + (tanggalTerbit.get(Calendar.YEAR) + 5);
        Random random = new Random();
        for(int i = 0; i < 2; i++) {
            nomorRegistrasi += (char) (random.nextInt(26) + 65);
        }
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
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

    public Calendar getTanggalTerbit() {
        return tanggalTerbit;
    }

    public void setTanggalTerbit(Calendar tanggalTerbit) {
        this.tanggalTerbit = tanggalTerbit;
    }

    public Calendar getTanggalKedaluwarsa() {
        return tanggalKedaluwarsa;
    }

    public void generateTanggalKedaluwarsa() {
        tanggalKedaluwarsa.set(tanggalTerbit.get(Calendar.YEAR) + 5,
                tanggalTerbit.get(Calendar.MONTH), tanggalTerbit.get(Calendar.DATE));
        this.tanggalKedaluwarsa = tanggalKedaluwarsa;
    }

    public BigInteger getHarga() {
        return harga;
    }

    public void setHarga(BigInteger harga) {
        this.harga = harga;
    }

    public List<GudangModel> getListGudang() {
        return listGudang;
    }

    public void setListGudang(List<GudangModel> listGudang) {
        this.listGudang = listGudang;
    }

    public List<SupplierModel> getListSupplier() {
        return listSupplier;
    }

    public void setListSupplier(List<SupplierModel> listSupplier) {
        this.listSupplier = listSupplier;
    }

    public JenisModel getJenisModel() {
        return jenisModel;
    }

    public void setJenisModel(JenisModel jenisModel) {
        this.jenisModel = jenisModel;
    }
}