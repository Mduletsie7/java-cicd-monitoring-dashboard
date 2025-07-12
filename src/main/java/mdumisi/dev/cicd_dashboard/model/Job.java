package mdumisi.dev.cicd_dashboard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pipeline_id")
    private Pipeline pipeline;

    private String jobId;
    private String jobName;
    private String stage;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private Integer duration;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;

    @Lob
    private String errorLog;

    @Lob
    private String output;
}
