package tugasapap.sidok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasapap.sidok.model.DokterModel;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long> {
    List<DokterModel> findAll();

    Optional<DokterModel> findByNik(String nik);

    Optional<DokterModel> findByNip(String nip);

    void delete(DokterModel dokter);

}
