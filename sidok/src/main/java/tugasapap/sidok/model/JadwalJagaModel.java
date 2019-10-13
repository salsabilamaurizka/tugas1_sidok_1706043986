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
@Table(name="jadwal_jaga")
public class JadwalJagaModel {
    //id,hari,id_poli,id_dokter
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotNull
    @Size(max = 20)
    @Column(name = "hari_jadwal_jaga", nullable = false)
    private String hari;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SpesialisasiDokterModel spesialisasiDokterModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JadwalJagaModel jadwalJagaModel;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public SpesialisasiDokterModel getSpesialisasiDokterModel() {
        return spesialisasiDokterModel;
    }

    public void setSpesialisasiDokterModel(SpesialisasiDokterModel spesialisasiDokterModel) {
        this.spesialisasiDokterModel = spesialisasiDokterModel;
    }

    public JadwalJagaModel getJadwalJagaModel() {
        return jadwalJagaModel;
    }

    public void setJadwalJagaModel(JadwalJagaModel jadwalJagaModel) {
        this.jadwalJagaModel = jadwalJagaModel;
    }
}
