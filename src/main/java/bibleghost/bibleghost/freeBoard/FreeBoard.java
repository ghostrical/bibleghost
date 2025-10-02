package bibleghost.bibleghost.freeBoard;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class FreeBoard {
    @Id
    @Column(name = "board_pk")
    private int boardPk;

    @Column(name = "title")
    private String title;

    @Column(name = "writer")
    private String writer;

    @Column(name = "source")
    private String source;

    @Column(name = "create_dt")
    private String createDt;

    @Column(name = "update_dt")
    private String updateDt;
}
