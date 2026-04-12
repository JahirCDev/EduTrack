package com.edutrack.api.grading;
import com.edutrack.api.group.Group;
import com.edutrack.api.group.GroupRepository;
import com.edutrack.api.summaries.GroupSummary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradingService {
  private final GradingSchemeRepository gradingSchemeRepository;
  private final GradingComponentRepository gradingComponentRepository;
  private final GroupRepository groupRepository;

  private GradingSchemeResponse toResponse(GradingScheme scheme, List<GradingComponent> components){
    return GradingSchemeResponse.builder()
    .id(scheme.getId())
    .group(GroupSummary.builder()
      .name(scheme.getGroup().getName())
      .teacherName(scheme.getGroup().getTeacher().getFirstName() + " " + scheme.getGroup().getTeacher().getLastName())
      .build()
    )
    .components(components.stream()
      .map(component -> GradingComponentResponse.builder()
        .id(component.getId())
        .schemeId(scheme.getId())
        .name(component.getName())
        .percentage(component.getPercentage())
        .type(component.getType())
        .build()
      )
      .collect(Collectors.toList())
    )
    .active(scheme.getActive())
    .build();
  }

  @Transactional
  public GradingSchemeResponse create(GradingSchemeRequest request) {
    if (request.getComponents() == null || request.getComponents().isEmpty()) {
      throw new RuntimeException("Debe incluir al menos un componente");
    }

    Set<ComponentType> types = new HashSet<>();
    for (GradingComponentRequest component : request.getComponents()) {
      if (component.getPercentage().doubleValue() <= 0 || component.getPercentage().doubleValue() > 100) {
        throw new RuntimeException("El porcentaje debe estar entre 0 y 100");
      }
      if (!types.add(component.getType())) {
        throw new RuntimeException("No se permiten tipos de componente duplicados");
      }
    }

    double total = request.getComponents().stream().mapToDouble(c -> c.getPercentage().doubleValue()).sum();

    if (Math.abs(total - 100.0) > 0.0001) {
      throw new RuntimeException("Los porcentajes deben sumar 100% ");
    }

    Group group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

    GradingScheme scheme = GradingScheme.builder()
    .group(group)
    .active(true)
    .build();

    GradingScheme saved = gradingSchemeRepository.save(scheme);
    
    List<GradingComponent> savedComponents = gradingComponentRepository.saveAll(request.getComponents().stream()
    .map(component -> GradingComponent.builder()
      .gradingScheme(saved)
      .name(component.getName())
      .percentage(component.getPercentage())
      .type(component.getType())
      .build()
    ).collect(Collectors.toList()));

    return toResponse(saved, savedComponents);
    
  }

  public GradingSchemeResponse findById(Long id) {
    GradingScheme scheme = gradingSchemeRepository.findById(id).orElseThrow(() -> new RuntimeException("Esquema de calificación no encontrado"));
    List<GradingComponent> components = gradingComponentRepository.findByGradingSchemeId(id);
    return toResponse(scheme, components);
  }

  public List<GradingSchemeResponse> findAll(){
    List<GradingScheme> schemes = gradingSchemeRepository.findAll();
    return schemes.stream().map(scheme -> {
      List<GradingComponent> components = gradingComponentRepository.findByGradingSchemeId(scheme.getId());
      return toResponse(scheme, components);
    }).collect(Collectors.toList());
  }

  @Transactional
  public GradingSchemeResponse update(Long id, GradingSchemeRequest request) {
    GradingScheme scheme = gradingSchemeRepository.findById(id).orElseThrow(() -> new RuntimeException("Esquema de calificación no encontrado"));

    Group group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
    if (request.getComponents() == null || request.getComponents().isEmpty()) {
      throw new RuntimeException("Debe incluir al menos un componente");
    }

    Set<ComponentType> types = new HashSet<>();
    for (GradingComponentRequest component : request.getComponents()) {
      if (component.getPercentage().doubleValue() <= 0 || component.getPercentage().doubleValue() > 100) {
        throw new RuntimeException("El porcentaje debe estar entre 0 y 100");
      }
      if (!types.add(component.getType())) {
        throw new RuntimeException("No se permiten tipos de componente duplicados");
      }
    }

    double total = request.getComponents().stream().mapToDouble(c -> c.getPercentage().doubleValue()).sum();
    if (Math.abs(total - 100.0) > 0.0001) {
      throw new RuntimeException("Los porcentajes deben sumar 100% ");
    }

    scheme.setGroup(group);
    gradingSchemeRepository.save(scheme);

    List<GradingComponent> existing = gradingComponentRepository.findByGradingSchemeId(id);
    Map<Long, GradingComponent> existingById = new HashMap<>();
    for (GradingComponent component : existing) {
      existingById.put(component.getId(), component);
    }

    Set<Long> requestIds = new HashSet<>();
    List<GradingComponent> toSave = new ArrayList<>();
    for (GradingComponentRequest component : request.getComponents()) {
      if (component.getId() != null) {
        GradingComponent current = existingById.get(component.getId());
        if (current == null) {
          throw new RuntimeException("Componente no encontrado");
        }
        current.setName(component.getName());
        current.setPercentage(component.getPercentage());
        current.setType(component.getType());
        toSave.add(current);
        requestIds.add(component.getId());
      } else {
        toSave.add(GradingComponent.builder()
        .gradingScheme(scheme)
        .name(component.getName())
        .percentage(component.getPercentage())
        .type(component.getType())
        .build());
      }
    }

    List<GradingComponent> toDelete = new ArrayList<>();
    for (GradingComponent component : existing) {
      if (!requestIds.contains(component.getId())) {
        toDelete.add(component);
      }
    }
    if (!toDelete.isEmpty()) {
      gradingComponentRepository.deleteAll(toDelete);
    }

    gradingComponentRepository.saveAll(toSave);
    List<GradingComponent> components = gradingComponentRepository.findByGradingSchemeId(id);
    return toResponse(scheme, components);
  }

  public void delete(Long id){
    GradingScheme scheme = gradingSchemeRepository.findById(id).orElseThrow(() -> new RuntimeException("Esquema de calificación no encontrado"));
    scheme.setActive(false);
    gradingSchemeRepository.save(scheme);
  }
}
