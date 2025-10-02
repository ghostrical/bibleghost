package bibleghost.bibleghost.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentFromToResponseDto {
    public String title;

    public String titleNm;

    public int chapter;

    public String chapterNm;

    public int verse;

    public String verseNm;

    public String object;
}
