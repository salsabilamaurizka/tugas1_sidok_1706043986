package tugasapap.sidok.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "spesialisasi_dokter")

public class SpesialisasiDokterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpesialisasiDokter;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "dokterId", referencedColumnName = "idDokter", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DokterModel DokterModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "spesialisasiId", referencedColumnName = "idSpesialisasi", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SpesialisasiModel spesialisasiModel;

    public Long getIdSpesialisasiDokter() {
        return idSpesialisasiDokter;
    }

    public void setIdSpesialisasiDokter(Long idSpesialisasiDokter) {
        this.idSpesialisasiDokter = idSpesialisasiDokter;
    }

    public tugasapap.sidok.model.DokterModel getDokterModel() {
        return DokterModel;
    }

    public void setDokterModel(tugasapap.sidok.model.DokterModel dokterModel) {
        DokterModel = dokterModel;
    }

    public SpesialisasiModel getSpesialisasiModel() {
        return spesialisasiModel;
    }

    public void setSpesialisasiModel(SpesialisasiModel spesialisasiModel) {
        this.spesialisasiModel = spesialisasiModel;
    }
}