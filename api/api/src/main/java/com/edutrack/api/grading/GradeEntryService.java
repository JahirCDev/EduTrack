package com.edutrack.api.grading;
import com.edutrack.api.student.Student;
import com.edutrack.api.student.StudentRepository;
import com.edutrack.api.summaries.ComponentSummary;
import com.edutrack.api.summaries.StudentSummary;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeEntryService {
  private final GradeEntryRepository gradeEntryRepository;
  private final StudentRepository studentRepository;
  private final GradingComponentRepository gradingComponentRepository;

  private GradeEntryResponse toResponse(GradeEntry gradeEntry) {
    return GradeEntryResponse.builder()
    .id(gradeEntry.getId())
    .student(StudentSummary.builder()
      .id(gradeEntry.getStudent().getId())
      .firstName(gradeEntry.getStudent().getFirstName())
      .lastName(gradeEntry.getStudent().getLastName())
      .email(gradeEntry.getStudent().getEmail())
      .build()
    )
    .component(ComponentSummary.builder()
      .id(gradeEntry.getComponent().getId())
      .name(gradeEntry.getComponent().getName())
      .percentage(gradeEntry.getComponent().getPercentage())
      .type(gradeEntry.getComponent().getType())
      .build()
    )
    .value(gradeEntry.getValue())
    .active(gradeEntry.getActive())
    .build();
  }

  public GradeEntryResponse create(GradeEntryRequest request){
    Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    GradingComponent gradingComponent = gradingComponentRepository.findById(request.getGradingComponentId()).orElseThrow(() -> new RuntimeException("Tipo de evaluación no encontrada"));

    GradeEntry gradeEntry = gradeEntryRepository.save(GradeEntry.builder()
      .student(student)
      .component(gradingComponent)
      .value(request.getValue())
      .build()
    );
    return toResponse(gradeEntry);
  }

  public GradeEntryResponse findById(Long id){
    GradeEntry gradeEntry = gradeEntryRepository.findById(id).orElseThrow(() -> new RuntimeException("Calificación no encontrada"));
    return toResponse(gradeEntry);
  }

  public List<GradeEntryResponse> findAll() {
    List<GradeEntry> gradeEntries = gradeEntryRepository.findAll();
    return gradeEntries.stream().map(this::toResponse).collect(Collectors.toList());    
  }

  public GradeEntryResponse update(Long id, GradeEntryRequest request) {
    GradeEntry gradeEntry = gradeEntryRepository.findById(id).orElseThrow(() -> new RuntimeException("Calificación no encontrada"));    
    Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    GradingComponent gradingComponent = gradingComponentRepository.findById(request.getGradingComponentId()).orElseThrow(() -> new RuntimeException("Tipo de evaluación no encontrada"));

    gradeEntry.setStudent(student);
    gradeEntry.setComponent(gradingComponent);
    gradeEntry.setValue(request.getValue());
    return toResponse(gradeEntryRepository.save(gradeEntry));
  }

  public void delete(Long id){
    GradeEntry gradeEntry = gradeEntryRepository.findById(id).orElseThrow(() -> new RuntimeException("Calificación no encontrada"));
    gradeEntry.setActive(false);
    gradeEntryRepository.save(gradeEntry);
  }
}
