package bibleghost.bibleghost.content;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ContentGetDto {
    private int tabPk;

    private String title;

    private int chapter;

    private int verse;

    private String object;

    private String langId;

    private String vsId;
}
