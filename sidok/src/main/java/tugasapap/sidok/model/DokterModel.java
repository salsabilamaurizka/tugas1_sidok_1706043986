package tugasapap.sidok.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.utility.RandomString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dokter")
public class DokterModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokter;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "nip", nullable = false)
    private String nip;

    @NotNull
    @Size(max = 255)
    @Column(name = "nik", nullable = false)
    private String nik;

    @NotNull
    @Column(name = "tanggalLahir", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempatLahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "jenisKelamin", nullable = false)
    private Integer jenisKelamin;

    @ManyToMany
    @JoinTable(
            name = "spesialisasi",
            joinColumns = @JoinColumn(name = "idDokter"),
            inverseJoinColumns = @JoinColumn(name = "idSpesialisasi"))
    List<SpesialisasiModel> listSpesialisasi;

    @ManyToMany
    @JoinTable(
            name = "poli",
            joinColumns = @JoinColumn(name = "idDokter"),
            inverseJoinColumns = @JoinColumn(name = "idSpesialisasi"))
    List<PoliModel> listPoli;

    public Long getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(Long idDokter) {
        this.idDokter = idDokter;
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

    public String generateNIPDokter() {
        String NIP ="";

        NIP += java.time.LocalDate.now().getYear() + 5;
        String tahun = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(tahun);
        String strDate = simpleDateFormat.format(getTanggalLahir()).replaceAll("-", "");
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder hurufTemp = new StringBuilder(2);
        for(int i = 0; i < 2; i++) {
            int index = (int) (AlphaNumericString.length()* Math.random());
            hurufTemp.append(AlphaNumericString.charAt(index));
        }
        String charRandom = hurufTemp.toString().toUpperCase();
        NIP += strDate;
        NIP += Integer.toString(getJenisKelamin());
        NIP += charRandom;

        return NIP;
    }
}
