package com.edutrack.api.period;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AcademicPeriodRepository extends JpaRepository <AcademicPeriod, Long>{
    Optional<AcademicPeriod> findFirstByActiveTrueOrderByStartDateDesc();
    List<AcademicPeriod> findAllByActiveTrue();

    @Query("SELECT COUNT(p) > 0 FROM AcademicPeriod p WHERE " +
       "p.startDate <= :endDate AND p.endDate >= :startDate ")
    boolean existsOverlappingPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
