package bibleghost.bibleghost.comm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommTabPK implements Serializable {
    private String commId;
    private String langId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommTabPK that = (CommTabPK) o;
        return Objects.equals(commId, that.commId) && Objects.equals(langId, that.langId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commId, langId);
    }

}
