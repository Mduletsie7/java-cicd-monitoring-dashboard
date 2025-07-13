package mdumisi.dev.cicd_dashboard.repository;

import mdumisi.dev.cicd_dashboard.model.Project;
import mdumisi.dev.cicd_dashboard.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByPlatform(Platform platform);
    Optional<Project> findByProjectId(String projectId);

    @Query("Select p FROM Project p where p.name LIKE %:name%")
    List<Project> findByNameContaining(String name);
}
