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
@Entity(name = "customers_projects")

public class CustomerProject implements BaseEntity<Long> {
    @Serial
    private static final long serialVersionUID = 6490508208761393007L;
    @Id
    @Column(name = "customers_id")
    private Long id;
    @Column(name = "projects_id")
    private Long projectId;
}
