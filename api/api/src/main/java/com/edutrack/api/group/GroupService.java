package com.edutrack.api.group;
import com.edutrack.api.teacher.Teacher;
import com.edutrack.api.period.AcademicPeriod;
import com.edutrack.api.teacher.TeacherRepository;
import com.edutrack.api.period.AcademicPeriodRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {
  private final GroupRepository groupRepository;  
  private final TeacherRepository teacherRepository;
  private final AcademicPeriodRepository academicPeriodRepository;

  private GroupResponse toResponse(Group group) {
    return GroupResponse.builder()
      .id(group.getId())
      .name(group.getName())
      .subject(group.getSubject())
      .teacher(TeacherSummary.builder()
        .id(group.getTeacher().getId())
        .firstName(group.getTeacher().getFirstName())
        .lastName(group.getTeacher().getLastName())
        .build())
      .period(PeriodSummary.builder()
        .id(group.getPeriod().getId())
        .name(group.getPeriod().getName())
        .build())
      .active(group.getActive())
      .build();
  }

  public GroupResponse create(GroupRequest request){
    Teacher teacher = teacherRepository.findById(request.getTeacherId()).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    AcademicPeriod academicPeriod = academicPeriodRepository.findById(request.getPeriodId()).orElseThrow(() -> new RuntimeException("Período no encontrado"));

    Group group = Group.builder()
    .name(request.getName())
    .subject(request.getSubject())
    .teacher(teacher)
    .period(academicPeriod)
    .active(true)
    .build();

    Group saved = groupRepository.save(group);

    return toResponse(saved);
  }

  public GroupResponse findById(Long id) {
    Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
    return toResponse(group);
  }

  public List<GroupResponse> findAll() {
    List<Group> groups = groupRepository.findAll();
    return groups.stream()
      .map(this::toResponse).collect(Collectors.toList());
  }

  public GroupResponse update(Long id, GroupRequest request) {
    Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

    Teacher teacher = teacherRepository.findById(request.getTeacherId()).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    AcademicPeriod academicPeriod = academicPeriodRepository.findById(request.getPeriodId()).orElseThrow(() -> new RuntimeException("Período no encontrado"));

    group.setName(request.getName());
    group.setSubject(request.getSubject());
    group.setTeacher(teacher);
    group.setPeriod(academicPeriod);

    Group update = groupRepository.save(group);

    return GroupResponse.builder()
      .id(update.getId())
      .name(update.getName())
      .subject(update.getSubject())
      .teacher(TeacherSummary.builder()
        .id(update.getTeacher().getId())
        .firstName(update.getTeacher().getFirstName())
        .lastName(update.getTeacher().getLastName())
        .build()
      )
      .period(PeriodSummary.builder()
        .id(update.getPeriod().getId())
        .name(update.getPeriod().getName())
        .build()
      )
      .active(update.getActive())
      .build();
  }

  public void delete(Long id) {
    Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
    group.setActive(false);
    groupRepository.save(group);
  }

  public List<GroupResponse> findByPeriod(Long periodId) {
    return groupRepository.findByPeriodId(periodId)
      .stream()
      .map(this::toResponse)
      .collect(Collectors.toList());
  }
}
