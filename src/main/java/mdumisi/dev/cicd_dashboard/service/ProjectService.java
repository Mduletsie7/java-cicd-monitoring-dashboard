package mdumisi.dev.cicd_dashboard.service;

import mdumisi.dev.cicd_dashboard.dto.ProjectDto;
import mdumisi.dev.cicd_dashboard.model.Project;
import mdumisi.dev.cicd_dashboard.model.PipelineStatus;
import mdumisi.dev.cicd_dashboard.repository.ProjectRepository;
import mdumisi.dev.cicd_dashboard.repository.PipelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PipelineRepository pipelineRepository;

    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ProjectDto> getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(this::convertToDto);
    }

    public ProjectDto createProject(Project project) {
        Project savedProject = projectRepository.save(project);
        return convertToDto(savedProject);
    }

    public Optional<ProjectDto> updateProject(Long id, Project projectDetails) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(projectDetails.getName());
                    project.setPlatform(projectDetails.getPlatform());
                    project.setRepositoryUrl(projectDetails.getRepositoryUrl());
                    project.setApiEndpoint(projectDetails.getApiEndpoint());
                    project.setAccessToken(projectDetails.getAccessToken());
                    return convertToDto(projectRepository.save(project));
                });
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectDto convertToDto(Project project) {
        // Get pipeline statistics
        Long totalPipelines = pipelineRepository.countByProjectIdAndStatus(project.getId(), null);
        Long successfulPipelines = pipelineRepository.countByProjectIdAndStatus(project.getId(), PipelineStatus.SUCCESS);
        Long failedPipelines = pipelineRepository.countByProjectIdAndStatus(project.getId(), PipelineStatus.FAILED);

        // Get last pipeline run
        LocalDateTime lastRun = pipelineRepository.findByProjectIdOrderByCreatedAtDesc(project.getId())
                .stream()
                .findFirst()
                .map(pipeline -> pipeline.getCreatedAt())
                .orElse(null);

        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .platform(project.getPlatform())
                .repositoryUrl(project.getRepositoryUrl())
                .createdAt(project.getCreatedAt())
                .lastPipelineRun(lastRun)
                .totalPipelines(totalPipelines.intValue())
                .successfulPipelines(successfulPipelines.intValue())
                .failedPipelines(failedPipelines.intValue())
                .build();
    }
}
