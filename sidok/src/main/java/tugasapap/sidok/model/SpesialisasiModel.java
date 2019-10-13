package tugasapap.sidok.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "spesialisasi")

public class SpesialisasiModel {
    //id,nama,gelar
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotNull
    @Size(max = 255)
    @Column(name="nama_spesialisasi", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name="gelar_spesialisasi", nullable = false)
    private String gelar;

    @OneToMany(mappedBy = "dokter", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SpesialisasiDokterModel> listSpesialisasiDokter;

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

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public List<SpesialisasiDokterModel> getListSpesialisasiDokter() {
        return listSpesialisasiDokter;
    }

    public void setListSpesialisasiDokter(List<SpesialisasiDokterModel> listSpesialisasiDokter) {
        this.listSpesialisasiDokter = listSpesialisasiDokter;
    }
}
