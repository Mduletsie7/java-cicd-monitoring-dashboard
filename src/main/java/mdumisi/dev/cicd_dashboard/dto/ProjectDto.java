package mdumisi.dev.cicd_dashboard.dto;

import mdumisi.dev.cicd_dashboard.model.Platform;
import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@Builder
public class ProjectDto {
    private Long id;
    private String name;
    private Platform platform;
    private String repositoryUrl;
    private LocalDateTime createdAt;
    private LocalDateTime lastPipelineRun;
    private Integer totalPipelines;
    private Integer successfulPipelines;
    private Integer failedPipelines;
}
