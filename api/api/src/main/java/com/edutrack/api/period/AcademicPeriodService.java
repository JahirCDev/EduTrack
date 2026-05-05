package com.edutrack.api.period;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AcademicPeriodService {
  private final AcademicPeriodRepository academicPeriodRepository;

  @Transactional
  public AcademicPeriodResponse create(AcademicPeriodRequest request){

    if(academicPeriodRepository.existsOverlappingPeriod(
      request.getStartDate(), request.getEndDate()
    )) {
      throw new RuntimeException("Ya hay un período activo en ese rango de fechas");
    }

    AcademicPeriod academicPeriod = AcademicPeriod.builder()
    .name(request.getName())
    .startDate(request.getStartDate())
    .endDate(request.getEndDate())
    .active(false)
    .build();

    AcademicPeriod saved = academicPeriodRepository.save(academicPeriod);

    return AcademicPeriodResponse.builder()
      .id(saved.getId())
      .name(saved.getName())
      .startDate(saved.getStartDate())
      .endDate(saved.getEndDate())
      .active(saved.getActive())
      .build();
  }

  public AcademicPeriodResponse findById(Long id) {
    AcademicPeriod academicPeriod = academicPeriodRepository.findById(id).orElseThrow(() -> new RuntimeException("Período no encontrado"));

    AcademicPeriodResponse response = AcademicPeriodResponse.builder()
      .id(academicPeriod.getId())
      .name(academicPeriod.getName())
      .startDate(academicPeriod.getStartDate())
      .endDate(academicPeriod.getEndDate())
      .active(academicPeriod.getActive())
      .build();

    return response;
  }

  public List<AcademicPeriodResponse> findAll() {
    List<AcademicPeriod> academicPeriods = academicPeriodRepository.findAll();
    return academicPeriods.stream()
                   .map(academicPeriod -> AcademicPeriodResponse.builder()
                    .id(academicPeriod.getId())
                    .name(academicPeriod.getName())
                    .startDate(academicPeriod.getStartDate())
                    .endDate(academicPeriod.getEndDate())
                    .active(academicPeriod.getActive())
                    .build()
                  )
                   .collect(Collectors.toList());
    
  }  


  public AcademicPeriodResponse update(Long id, AcademicPeriodRequest request){
    AcademicPeriod academicPeriod = academicPeriodRepository.findById(id).orElseThrow(() -> new RuntimeException("Período no encontrado"));

    academicPeriod.setName(request.getName());
    academicPeriod.setStartDate(request.getStartDate());
    academicPeriod.setEndDate(request.getEndDate());

    AcademicPeriod update = academicPeriodRepository.save(academicPeriod);

    return AcademicPeriodResponse.builder()
    .id(update.getId())
    .name(update.getName())
    .startDate(update.getStartDate())
    .endDate(update.getEndDate())
    .active(update.getActive())
    .build();
  }

  public void delete(Long id) {
    AcademicPeriod academicPeriod = academicPeriodRepository.findById(id).orElseThrow(() -> new RuntimeException("Período no encontrado"));
    academicPeriod.setActive(false);
    academicPeriodRepository.save(academicPeriod);
  } 

  public AcademicPeriodResponse findActive(){
    AcademicPeriod academicPeriod = academicPeriodRepository.findFirstByActiveTrueOrderByStartDateDesc().orElseThrow(() -> new RuntimeException("Período inactivo"));

    AcademicPeriodResponse response = AcademicPeriodResponse.builder()
      .id(academicPeriod.getId())
      .name(academicPeriod.getName())
      .startDate(academicPeriod.getStartDate())
      .endDate(academicPeriod.getEndDate())
      .active(academicPeriod.getActive())
      .build();

    return response;
  }

} 
