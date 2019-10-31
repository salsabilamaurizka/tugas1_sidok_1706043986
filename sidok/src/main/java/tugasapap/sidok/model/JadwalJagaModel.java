package tugasapap.sidok.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="jadwalJaga")
public class JadwalJagaModel implements Serializable {
    //id,hari,id_poli,id_dokter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJadwalJaga;

    @NotNull
    @Size(max = 20)
    @Column(name = "hari", nullable = false)
    private String hari;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "poliId", referencedColumnName = "idPoli", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoliModel poli;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dokterId", referencedColumnName = "idDokter", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DokterModel dokter;

    public Long getIdJadwalJaga() {
        return idJadwalJaga;
    }

    public void setIdJadwalJaga(Long idJadwalJaga) {
        this.idJadwalJaga = idJadwalJaga;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public PoliModel getPoli() {
        return poli;
    }

    public void setPoli(PoliModel poli) {
        this.poli = poli;
    }

    public DokterModel getDokter() {
        return dokter;
    }

    public void setDokter(DokterModel dokter) {
        this.dokter = dokter;
    }
}
