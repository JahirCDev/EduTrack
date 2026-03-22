package com.edutrack.api.period;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/periods")
@RequiredArgsConstructor
public class AcademicPeriodController {
  private final AcademicPeriodService academicPeriodService;
  
  @GetMapping("/active")
  public AcademicPeriodResponse findActive() {
    return academicPeriodService.findActive();
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  public AcademicPeriodResponse create(@RequestBody AcademicPeriodRequest request) {
    return academicPeriodService.create(request);
  }

  @GetMapping("/{id}")
  public AcademicPeriodResponse findById(@PathVariable Long id) {      
    return academicPeriodService.findById(id);
  }

  @GetMapping
  public List<AcademicPeriodResponse> findAll() {
    return academicPeriodService.findAll();
  }
  
  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public AcademicPeriodResponse update(@PathVariable Long id, @RequestBody AcademicPeriodRequest request) {      
    return academicPeriodService.update(id, request);
  }
  
  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}") 
  public void delete(@PathVariable Long id){
    academicPeriodService.delete(id);
  }
}
