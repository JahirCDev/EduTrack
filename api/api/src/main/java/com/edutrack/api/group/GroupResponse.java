  package com.edutrack.api.group;
  import lombok.Builder;
  import lombok.Data;
  import lombok.NoArgsConstructor;

import com.edutrack.api.summaries.PeriodSummary;
import com.edutrack.api.summaries.TeacherSummary;

import lombok.AllArgsConstructor;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public class GroupResponse {
    private Long id;
    private String name;
    private String subject;
    private TeacherSummary teacher;
    private PeriodSummary period;
    private Boolean active;
  }
