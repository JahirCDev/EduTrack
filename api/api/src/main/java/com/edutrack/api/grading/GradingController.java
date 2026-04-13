package com.edutrack.api.grading;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grading-schemes")
@RequiredArgsConstructor
public class GradingController {
  private final GradingService gradingService;

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  public GradingSchemeResponse create(@Valid @RequestBody GradingSchemeRequest request) {      
    return gradingService.create(request);
  }

  @GetMapping
  public List<GradingSchemeResponse> findAll() {
    return gradingService.findAll();
  }

  @GetMapping("/{id}")
  public GradingSchemeResponse findById(@PathVariable Long id) {
    return gradingService.findById(id);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public GradingSchemeResponse update(@Valid @PathVariable Long id, @RequestBody GradingSchemeRequest request) {      
    return gradingService.update(id, request);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    gradingService.delete(id);
  }
}
