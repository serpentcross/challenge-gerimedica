package nl.gerimedica.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {
  private String source;
  private String codeListCode;
  private String code;
  private String displayValue;
  private String longDescription;
  private String fromDate;
  private String toDate;
  private Integer sortingPriority;
}