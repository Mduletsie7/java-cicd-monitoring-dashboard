package mdumisi.dev.cicd_dashboard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pipelines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pipeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private String pipelineId;
    private String ref;
    private String sha;

    @Enumerated(EnumType.STRING)
    private PipelineStatus status;

    private Integer duration;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "pipeline", cascade = CascadeType.ALL)
    private List<Job> jobs;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
