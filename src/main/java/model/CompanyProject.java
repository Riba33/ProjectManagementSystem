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
@Entity(name = "companies_projects")

public class CompanyProject implements BaseEntity<Long> {
    @Serial
    private static final long serialVersionUID = -2690957991724417000L;
    @Id
    @Column(name = "companies_id")
    private Long id;
    @Column(name = "projects_id")
    private Long projectId;
}
