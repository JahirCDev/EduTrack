package com.edutrack.api.grading;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grade-entries")
@RequiredArgsConstructor
public class GradeEntryController {
  private final GradeEntryService gradeEntryService;
  
  @PostMapping
  public GradeEntryResponse create(@RequestBody GradeEntryRequest request) {      
    return gradeEntryService.create(request);
  }

  @GetMapping
  public List<GradeEntryResponse> findAll() {
    return gradeEntryService.findAll();
  }

  @GetMapping("/{id}")
  public GradeEntryResponse findById(@PathVariable Long id) {
    return gradeEntryService.findById(id);
  }

  @PutMapping("/{id}")
  public GradeEntryResponse update(@PathVariable Long id, @RequestBody GradeEntryRequest request) {      
    return gradeEntryService.update(id, request);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    gradeEntryService.delete(id);
  }
}
