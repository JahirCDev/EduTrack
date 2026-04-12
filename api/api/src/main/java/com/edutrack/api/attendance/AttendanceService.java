package com.edutrack.api.attendance;
import com.edutrack.api.group.Group;
import com.edutrack.api.group.GroupRepository;
import com.edutrack.api.student.Student;
import com.edutrack.api.student.StudentRepository;
import com.edutrack.api.summaries.GroupSummary;
import com.edutrack.api.summaries.StudentSummary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {
  private final AttendanceRecordRepository attendanceRecordRepository;
  private final AttendanceSessionRepository attendanceSessionRepository;
  private final GroupRepository groupRepository;
  private final StudentRepository studentRepository;

  private AttendanceSessionResponse toResponse(AttendanceSession attendance, List<AttendanceRecord> records) {
    return AttendanceSessionResponse.builder()
    .id(attendance.getId())
    .group(GroupSummary.builder()
      .name(attendance.getGroup().getName())
      .teacherName(attendance.getGroup().getTeacher().getFirstName() + " " + attendance.getGroup().getTeacher().getLastName())
      .build()
    )
    .sessionDate(attendance.getSessionDate())
    .notes(attendance.getNotes())
    .records(records.stream()
      .map(record -> AttendanceRecordResponse.builder()
        .id(record.getId())
        .sessionId(record.getSession().getId())
        .student(StudentSummary.builder()
          .id(record.getStudent().getId())
          .firstName(record.getStudent().getFirstName())
          .lastName(record.getStudent().getLastName())
          .build())
        .status(record.getStatus())
        .notes(record.getNotes())
        .build()
      ).collect(Collectors.toList())
    )
    .build();
  }

  @Transactional
  public AttendanceSessionResponse create(AttendanceSessionRequest request) {
    Group group = groupRepository.findById(request.getGroupId())
    .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

    AttendanceSession session = AttendanceSession.builder()
    .group(group)
    .sessionDate(request.getSessionDate())
    .notes(request.getNotes())
    .build();

    AttendanceSession savedSession = attendanceSessionRepository.save(session);

    Set<Long> studentIds = new HashSet<>();
    List<AttendanceRecord> records = request.getRecords().stream()
    .map(record -> {
      if (!studentIds.add(record.getStudentId())) {
        throw new RuntimeException("No se permiten estudiantes duplicados en la sesión");
      }
      Student student = studentRepository.findById(record.getStudentId())
      .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

      return AttendanceRecord.builder()
      .session(savedSession)
      .student(student)
      .status(record.getStatus())
      .notes(record.getNotes())
      .build();
    })
    .collect(Collectors.toList());

    List<AttendanceRecord> savedRecords = attendanceRecordRepository.saveAll(records);
    return toResponse(savedSession, savedRecords);
  }
  
  public AttendanceSessionResponse findById(Long id) {
    AttendanceSession session = attendanceSessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Sesión de clase no encontrado"));
    List<AttendanceRecord> records = attendanceRecordRepository.findBySessionId(id);
    return toResponse(session, records);
  }

  public List<AttendanceSessionResponse> findAll() {
    List<AttendanceSession> sessions = attendanceSessionRepository.findAll();
    return sessions.stream().map(session -> {
      List<AttendanceRecord> records = attendanceRecordRepository.findBySessionId(session.getId());
      return toResponse(session, records);
    }).collect(Collectors.toList());
  }

  @Transactional
  public AttendanceSessionResponse update(Long id, AttendanceSessionRequest request) {
    AttendanceSession session = attendanceSessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Sesión de clase no encontrado"));

    Group group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
    
    session.setGroup(group);
    session.setSessionDate(request.getSessionDate());
    session.setNotes(request.getNotes());

    attendanceSessionRepository.save(session);

    List<AttendanceRecord> existing = attendanceRecordRepository.findBySessionId(id);
    Map<Long, AttendanceRecord> existingById = new HashMap<>();
    for (AttendanceRecord record : existing) {
      existingById.put(record.getId(), record);
    }

    Set<Long> requestIds = new HashSet<>();
    Set<Long> studentIds = new HashSet<>();
    List<AttendanceRecord> toSave = new ArrayList<>();
    for (AttendanceRecordRequest record : request.getRecords()) {
      if (!studentIds.add(record.getStudentId())) {
        throw new RuntimeException("No se permiten estudiantes duplicados en la sesión");
      }
      Student student = studentRepository.findById(record.getStudentId())
      .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

      if (record.getId() != null) {
        AttendanceRecord current = existingById.get(record.getId());
        if (current == null) {
          throw new RuntimeException("Registro de asistencia no encontrado");
        }
        current.setStudent(student);
        current.setStatus(record.getStatus());
        current.setNotes(record.getNotes());
        toSave.add(current);
        requestIds.add(record.getId());
      } else {
        toSave.add(AttendanceRecord.builder()
        .session(session)
        .student(student)
        .status(record.getStatus())
        .notes(record.getNotes())
        .build());
      }
    }

    List<AttendanceRecord> toDelete = new ArrayList<>();
    for (AttendanceRecord record : existing) {
      if (!requestIds.contains(record.getId())) {
        toDelete.add(record);
      }
    }
    if (!toDelete.isEmpty()) {
      attendanceRecordRepository.deleteAll(toDelete);
    }

    attendanceRecordRepository.saveAll(toSave);
    List<AttendanceRecord> records = attendanceRecordRepository.findBySessionId(id);
    return toResponse(session, records);
  }

  public void delete(Long id){
    AttendanceSession session = attendanceSessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Sesión de clase no encontrado"));
    session.setActive(false);
    attendanceSessionRepository.save(session);
  }
}
