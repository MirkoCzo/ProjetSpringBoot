package be.condorcet.projetsb.modele;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class InfosKey implements Serializable {
    @Column(name = "IDSESSIONCOURS")
    private Integer idsessioncours;

    @Column(name = "IDFORMATEUR")
    private Integer idformateur;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfosKey infosKey = (InfosKey) o;
        return idsessioncours.equals(infosKey.idsessioncours) && idformateur.equals(infosKey.idformateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsessioncours, idformateur);
    }
}
