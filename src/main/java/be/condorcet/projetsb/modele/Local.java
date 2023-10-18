package be.condorcet.projetsb.modele;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APILOCAL", schema = "ORA2", catalog = "XE")
public class Local {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "local_generator")
    @SequenceGenerator(name="local_generator", sequenceName = "APILOCAL_SEQ", allocationSize = 1)
    private Integer id_local;

    @NonNull
    private String sigle;

    @NonNull
    private Integer places;

    @NonNull
    private String description;
}
