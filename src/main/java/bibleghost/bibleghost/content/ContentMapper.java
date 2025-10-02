package bibleghost.bibleghost.content;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContentMapper {

    public Content ContentGetDtoToContent(ContentGetDto contentGetDto) {
        Content content = new Content();

        if(contentGetDto != null) {
            content.setTabPk(contentGetDto.getTabPk());
            content.setTitle(contentGetDto.getTitle());
            content.setChapter(contentGetDto.getChapter());
            content.setVerse(contentGetDto.getVerse());
            content.setObject(contentGetDto.getObject());
            content.setLangId(contentGetDto.getLangId());
            content.setVsId(contentGetDto.getVsId());
        }

        return content;
    }

    public ContentResponseDto ContentToContentResponseDto(Content content){
        ContentResponseDto contentResponseDto = new ContentResponseDto();

        if(content != null){
            contentResponseDto.setTabPk(content.getTabPk());
            contentResponseDto.setTitle(content.getTitle());
            contentResponseDto.setChapter(content.getChapter());
            contentResponseDto.setVerse(content.getVerse());
            contentResponseDto.setObject(content.getObject());
            contentResponseDto.setLangId(content.getLangId());
            contentResponseDto.setVsId(content.getVsId());
            contentResponseDto.setTitleNm(content.getTitleNm());
            contentResponseDto.setVsNm(content.getVsNm());
            contentResponseDto.setLangNm(content.getLangNm());
        }

        return contentResponseDto;
    }

    public List<ContentResponseDto> ContentsToContentResponseDtos(List<Content> contents){
        List<ContentResponseDto> contentResponseDtos = new ArrayList<>();

        if(contents.size() != 0){

            for(Content c : contents){
                ContentResponseDto contentResponseDto = new ContentResponseDto();

                contentResponseDto.setTabPk(c.getTabPk());
                contentResponseDto.setTitle(c.getTitle());
                contentResponseDto.setChapter(c.getChapter());
                contentResponseDto.setVerse(c.getVerse());
                contentResponseDto.setObject(c.getObject());
                contentResponseDto.setLangId(c.getLangId());
                contentResponseDto.setVsId(c.getVsId());
                contentResponseDto.setTitleNm(c.getTitleNm());
                contentResponseDto.setVsNm(c.getVsNm());
                contentResponseDto.setLangNm(c.getLangNm());

                contentResponseDtos.add(contentResponseDto);
            }
        }

        return contentResponseDtos;
    }
}
