package bibleghost.bibleghost.content;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ContentResponseDto {
    private int tabPk;

    private String title;

    private String titleNm;

    private int chapter;

    private int verse;

    private String object;

    private String langId;

    private String langNm;

    private String vsId;

    private String vsNm;
}
