package mdumisi.dev.cicd_dashboard.integration;

import mdumisi.dev.cicd_dashboard.dto.PipelineDto;
import mdumisi.dev.cicd_dashboard.dto.JobDto;
import mdumisi.dev.cicd_dashboard.model.Project;
import java.util.List;

public interface CicdIntegrationService {
    List<PipelineDto> fetchPipelines(Project project);
    List<JobDto> fetchJobs(Project project, String pipelineId);
    PipelineDto fetchPipelineDetails(Project project, String pipelineId);
    boolean testConnection(Project project);
}
