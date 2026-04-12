package com.edutrack.api.period;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcademicPeriodService {
  private final AcademicPeriodRepository academicPeriodRepository;

  private AcademicPeriodResponse toResponse(AcademicPeriod academicPeriod) {
    return AcademicPeriodResponse.builder()
    .id(academicPeriod.getId())
    .name(academicPeriod.getName())
    .startDate(academicPeriod.getStartDate())
    .endDate(academicPeriod.getEndDate())
    .active(academicPeriod.getActive())
    .build();
  }

  @Transactional
  public AcademicPeriodResponse create(AcademicPeriodRequest request){
    if(academicPeriodRepository.existsOverlappingPeriod(request.getStartDate(), request.getEndDate())) {
      throw new RuntimeException("Ya hay un período activo en ese rango de fechas");
    }

    AcademicPeriod academicPeriod = academicPeriodRepository.save(AcademicPeriod.builder()
      .name(request.getName())
      .startDate(request.getStartDate())
      .endDate(request.getEndDate())
      .active(false)
      .build()
    );

    return toResponse(academicPeriod);
  }

  public AcademicPeriodResponse findById(Long id) {
    AcademicPeriod academicPeriod = academicPeriodRepository.findById(id).orElseThrow(() -> new RuntimeException("Período no encontrado"));
    return toResponse(academicPeriod);
  }

  public List<AcademicPeriodResponse> findAll() {
    List<AcademicPeriod> academicPeriods = academicPeriodRepository.findAll();
    return academicPeriods.stream().map(this::toResponse).collect(Collectors.toList());
  }  

  @Transactional
  public AcademicPeriodResponse update(Long id, AcademicPeriodRequest request){
    AcademicPeriod academicPeriod = academicPeriodRepository.findById(id).orElseThrow(() -> new RuntimeException("Período no encontrado"));

    academicPeriod.setName(request.getName());
    academicPeriod.setStartDate(request.getStartDate());
    academicPeriod.setEndDate(request.getEndDate());
    return toResponse(academicPeriodRepository.save(academicPeriod));
  }

  public void delete(Long id) {
    AcademicPeriod academicPeriod = academicPeriodRepository.findById(id).orElseThrow(() -> new RuntimeException("Período no encontrado"));
    academicPeriod.setActive(false);
    academicPeriodRepository.save(academicPeriod);
  } 

  public AcademicPeriodResponse findActive(){
    AcademicPeriod academicPeriod = academicPeriodRepository.findFirstByActiveTrueOrderByStartDateDesc().orElseThrow(() -> new RuntimeException("Período inactivo"));
    return toResponse(academicPeriod);
  }
} 
