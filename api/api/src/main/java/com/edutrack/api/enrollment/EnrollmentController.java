package com.edutrack.api.enrollment;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
  private final EnrollmentService enrollmentService;

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  public EnrollmentResponse create(@RequestBody EnrollmentRequest request) {      
    return enrollmentService.create(request);
  }
  
  @GetMapping
  public List<EnrollmentResponse> findAll() {
    return enrollmentService.findAll();
  }

  @GetMapping("/{id}")
  public EnrollmentResponse findById(@PathVariable Long id) {
    return enrollmentService.findById(id);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public EnrollmentResponse update(@PathVariable Long id, @RequestBody EnrollmentRequest request) {      
    return enrollmentService.update(id, request);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    enrollmentService.delete(id);
  }
  
}
