package com.edutrack.api.group;
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
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
  private final GroupService groupService;
  
  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  public GroupResponse create(@RequestBody GroupRequest request) {
    return groupService.create(request);
  }

  @GetMapping
  public List<GroupResponse> findAll() {
    return groupService.findAll();
  }
  
  @GetMapping("/{id}")
  public GroupResponse findById(@PathVariable Long id) {
    return groupService.findById(id);
  }
  
  @GetMapping("/period/{periodId}")
  public List<GroupResponse> findByPeriod(@PathVariable Long periodId) {
    return groupService.findByPeriod(periodId);
  }
    
  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest request){
    return groupService.update(id, request);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    groupService.delete(id);
  }
}
