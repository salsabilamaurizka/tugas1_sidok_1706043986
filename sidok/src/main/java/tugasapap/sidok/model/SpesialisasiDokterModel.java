package tugasapap.sidok.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "spesialisasi_dokter")
public class SpesialisasiDokterModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpesialisasiDokter;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dokterId", referencedColumnName = "idDokter", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DokterModel dokter;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "spesialisasiId", referencedColumnName = "idSpesialisasi", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SpesialisasiModel spesialisasi;

    public Long getIdSpesialisasiDokter() {
        return idSpesialisasiDokter;
    }

    public void setIdSpesialisasiDokter(Long idSpesialisasiDokter) {
        this.idSpesialisasiDokter = idSpesialisasiDokter;
    }

    public DokterModel getDokter() {
        return dokter;
    }

    public void setDokter(DokterModel dokter) {
        this.dokter = dokter;
    }

    public SpesialisasiModel getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(SpesialisasiModel spesialisasi) {
        this.spesialisasi = spesialisasi;
    }
}
