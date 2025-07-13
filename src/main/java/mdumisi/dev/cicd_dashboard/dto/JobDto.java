package mdumisi.dev.cicd_dashboard.dto;

import mdumisi.dev.cicd_dashboard.model.JobStatus;
import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@Builder
public class JobDto {
    private Long id;
    private String jobId;
    private String jobName;
    private String stage;
    private JobStatus status;
    private Integer duration;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private String errorLog;
}
