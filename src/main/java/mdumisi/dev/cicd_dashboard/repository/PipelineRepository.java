package mdumisi.dev.cicd_dashboard.repository;

import mdumisi.dev.cicd_dashboard.model.Pipeline;
import mdumisi.dev.cicd_dashboard.model.PipelineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Long> {
    List<Pipeline> findByProjectIdOrderByCreatedAtDesc(Long projectId);
    List<Pipeline> findByStatus(PipelineStatus status);

    @Query("SELECT p FROM Pipeline p WHERE p.project.id = :projectId AND p.createdAt >= :since")
    List<Pipeline> findRecentPipelines(@Param("projectId") Long projectId, @Param("since") LocalDateTime since);

    @Query("SELECT p FROM Pipeline p WHERE p.status = 'RUNNING' OR p.status = 'PENDING'")
    List<Pipeline> findActivePipelines();

    @Query("SELECT COUNT(p) FROM Pipeline p WHERE p.project.id = :projectId AND p.status = :status")
    Long countByProjectIdAndStatus(@Param("projectId") Long projectId, @Param("status") PipelineStatus status);
}
