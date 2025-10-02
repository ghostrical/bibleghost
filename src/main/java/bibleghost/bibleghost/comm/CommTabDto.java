package bibleghost.bibleghost.comm;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class CommTabDto {
    private String commId;
    private String langId;
    private String commNm;
    private String langNm;
}
