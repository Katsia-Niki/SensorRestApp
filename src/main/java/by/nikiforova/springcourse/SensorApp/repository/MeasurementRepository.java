package by.nikiforova.springcourse.SensorApp.repository;

import by.nikiforova.springcourse.SensorApp.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, String> {
}
