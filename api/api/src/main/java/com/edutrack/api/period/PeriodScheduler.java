package com.edutrack.api.period;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

@Component
@RequiredArgsConstructor
public class PeriodScheduler {
    private final AcademicPeriodRepository academicPeriodRepository;

    @Scheduled(cron = "0 0 0 * * *")  // se ejecuta cada día a medianoche
    @Transactional
    public void updatePeriodStatus() {
        LocalDate today = LocalDate.now();

        // Activar períodos cuya fecha de inicio llegó
        academicPeriodRepository.findAll().stream()
            .filter(p -> !p.getActive() 
                && !today.isBefore(p.getStartDate()) 
                && !today.isAfter(p.getEndDate()))
            .forEach(p -> {
                p.setActive(true);
                academicPeriodRepository.save(p);
            });

        // Desactivar períodos cuya fecha de fin pasó
        academicPeriodRepository.findAllByActiveTrue().stream()
            .filter(p -> today.isAfter(p.getEndDate()))
            .forEach(p -> {
                p.setActive(false);
                academicPeriodRepository.save(p);
            });
    }
}

