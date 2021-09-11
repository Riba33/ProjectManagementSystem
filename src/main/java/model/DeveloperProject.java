package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "developers_projects")

public class DeveloperProject implements BaseEntity<Long> {
    @Serial
    private static final long serialVersionUID = 3382718755534266434L;
    @Id
    @Column(name = "developers_id")
    private Long id;
    @Column(name = "projects_id")
    private Long projectId;
}
