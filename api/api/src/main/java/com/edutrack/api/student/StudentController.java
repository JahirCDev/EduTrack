package com.edutrack.api.student;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
  private final StudentService studentService;
  
  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  public StudentResponse create(@RequestBody StudentRequest request){
    return studentService.create(request);
  }

  @GetMapping
  public List<StudentResponse> findAll() {
    return studentService.findAll();
  }

  @GetMapping("/{id}")
  public StudentResponse findById(@PathVariable Long id){
    return studentService.findById(id);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request) {      
    return studentService.update(id, request);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    studentService.delete(id);
  }
}
