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
@Entity(name = "developers_skils")

public class DeveloperSkill implements BaseEntity<Long> {

    @Serial
    private static final long serialVersionUID = -7493607710525159581L;
    @Id
    @Column(name = "developers_id")
    private Long id;
    @Column(name = "skils_id")
    private Long skillId;
}
