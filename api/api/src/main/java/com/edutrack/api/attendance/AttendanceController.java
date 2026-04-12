package com.edutrack.api.attendance;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attendance-sessions")
@RequiredArgsConstructor
public class AttendanceController {
  private final AttendanceService attendanceService;

  @PostMapping
  public AttendanceSessionResponse create(@Valid @RequestBody AttendanceSessionRequest request) {      
    return attendanceService.create(request);
  }
  
  @GetMapping
  public List<AttendanceSessionResponse> findAll() {
    return attendanceService.findAll();
  }

  @GetMapping("/{id}")
  public AttendanceSessionResponse findById(@PathVariable Long id) {
    return attendanceService.findById(id);
  }
  
  @PutMapping("/{id}")
  public AttendanceSessionResponse update(@Valid @PathVariable Long id, @RequestBody AttendanceSessionRequest request) {
    return attendanceService.update(id, request);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    attendanceService.delete(id);
  }
}
