package com.edutrack.api.teacher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
  private final TeacherService teacherService;

  @PostMapping
  public TeacherResponse create(@RequestBody TeacherRequest request) {
    return teacherService.create(request);
  }
  
  @GetMapping
  public List<TeacherResponse> findAll() {
    return teacherService.findAll();
  }
  
  @GetMapping("/{id}")
  public TeacherResponse findById(@PathVariable Long id) {
    return teacherService.findById(id);
  }

  @PutMapping("/{id}")
  public TeacherResponse update(@PathVariable Long id, @RequestBody TeacherRequest request) {      
      return teacherService.update(id, request);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    teacherService.delete(id);
  }
}
