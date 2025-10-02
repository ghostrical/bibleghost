package bibleghost.bibleghost.freeBoard;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FreeBoardDto {
    private int boardPk;

    private String title;

    private String writer;

    private String source;

    private String createDt;

    private String updateDt;
}
