package bibleghost.bibleghost.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentFromToRequestDto {
    public String fromTitleNum;

    public int fromChapterNum;

    public int fromVerseNum;

    public String toTitleNum;

    public int toChapterNum;

    public int toVerseNum;

}
