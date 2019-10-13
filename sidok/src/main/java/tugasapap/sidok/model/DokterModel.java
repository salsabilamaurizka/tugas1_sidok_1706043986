package tugasapap.sidok.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dokter")

public class DokterModel implements Serializable {
    // id gatau
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_dokter", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "NIP_dokter", nullable = false)
    private String nip;

    @NotNull
    @Size(max = 255)
    @Column(name = "NIK_dokter", nullable = false)
    private String nik;

    @NotNull
    @Column(name = "tanggal_lahir_dokter", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_lahir_dokter", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "jenis_kelamin_dokter", nullable = false)
    private Integer jenisKelamin;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SpesialisasiDokterModel> listSpesialisasiDokter;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<JadwalJagaModel> listJadwalJaga;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public List<SpesialisasiDokterModel> getListSpesialisasiDokter() {
        return listSpesialisasiDokter;
    }

    public void setListSpesialisasiDokter(List<SpesialisasiDokterModel> listSpesialisasiDokter) {
        this.listSpesialisasiDokter = listSpesialisasiDokter;
    }

    public List<JadwalJagaModel> getListJadwalJaga() {
        return listJadwalJaga;
    }

    public void setListJadwalJaga(List<JadwalJagaModel> listJadwalJaga) {
        this.listJadwalJaga = listJadwalJaga;
    }
}
