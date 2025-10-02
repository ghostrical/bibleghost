package bibleghost.bibleghost.comm;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(CommTabPK.class)
public class CommTab {

    @Id
    @Column(name = "comm_id")
    private String commId;

    @Id
    @Column(name = "lang_id")
    private String langId;

    @Column(name = "comm_nm")
    private String commNm;

}
