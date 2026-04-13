package com.edutrack.api.attendance;
import com.edutrack.api.summaries.GroupSummary;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceSessionResponse {
  private Long id;
  private GroupSummary group;
  private List<AttendanceRecordResponse> records;
  private LocalDate sessionDate;
  private String notes;  
}
