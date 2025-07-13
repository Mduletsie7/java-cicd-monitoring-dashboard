package mdumisi.dev.cicd_dashboard.dto;

import mdumisi.dev.cicd_dashboard.model.PipelineStatus;
import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PipelineDto {
    private Long id;
    private String pipelineId;
    private String ref;
    private String sha;
    private PipelineStatus status;
    private Integer duration;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private List<JobDto> jobs;
    private String projectName;
}
