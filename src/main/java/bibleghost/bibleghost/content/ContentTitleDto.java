package bibleghost.bibleghost.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentTitleDto {
    public String title;

    public String titleNm;

    public int chapter;

    public String chapterNm;
}
