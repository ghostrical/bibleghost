package bibleghost.bibleghost.content;

import bibleghost.bibleghost.comm.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContentService {

    @Autowired
    private final ContentRepository contentRepository;

    @Autowired
    private final ContentCrossRepository contentCrossRepository;

    @Autowired
    private final CommTabRepository commTabRepository;

    @Autowired
    private final LangTabRepository langTabRepository;

    public ContentService(ContentRepository contentRepository,
                          CommTabRepository commTabRepository,
                          LangTabRepository langTabRepository,
                          ContentCrossRepository contentCrossRepository){
        this.contentRepository = contentRepository;
        this.commTabRepository = commTabRepository;
        this.langTabRepository = langTabRepository;
        this.contentCrossRepository = contentCrossRepository;
    }

    public List<Content> findContents() {
        List<Content> contents = contentRepository.findAll();

        List<CommTab> commTabs = commTabRepository.findAllByKor();

        if(contents.size() != 0) {
            for (Content c : contents) {
                String getTitleNm = "";
                String getVsNm = "";
//
                for (CommTab com1 : commTabs) {
                    if (com1.getCommId().equals(c.getTitle())) {
                        getTitleNm = com1.getCommNm();
                        break;
                    }
                }
//
                for (CommTab com2 : commTabs) {
                    if (com2.getCommId().equals(c.getVsId())) {
                        getVsNm = com2.getCommNm();
                        break;
                    }
                }

                c.setTitleNm(getTitleNm);
                c.setVsNm(getVsNm);
            }
        }


        return contents;
    }

    public Content findContent(int tabPk) {
        Optional<Content> optionalContent = contentRepository.findAllByTabPk(tabPk);

        Content content = optionalContent.orElseThrow();

        List<LangTab> langTabs = langTabRepository.findAll();

        if(content != null) {
                String getTitleNm = "";
                String getVsNm = "";
                String getLangNm = "";

                for (LangTab la : langTabs) {

                    if (la.getLangId().equals(content.getLangId())) {

                        getLangNm = la.getLangNm();
                        break;
                    }
                }

                content.setTitleNm(getTitleNm);
                content.setVsNm(getVsNm);
                content.setLangNm(getLangNm);
        }

        return content;
    }

    public List<CommTabDto> findComms() {
        List<CommTab> commTabs = commTabRepository.findAll();

        List<CommTabDto> contents = new ArrayList<>();

        List<LangTab> langTabs = langTabRepository.findAll();

        for(CommTab c : commTabs){
            CommTabDto commTabDto = new CommTabDto();
            commTabDto.setCommId(c.getCommId());
            commTabDto.setLangId(c.getLangId());
            commTabDto.setCommNm(c.getCommNm());

            for(int i = 0; i < langTabs.size(); i++){

                if(langTabs.get(i).getLangId().equals(c.getLangId())){

                    commTabDto.setLangNm(langTabs.get(i).getLangNm());
                }
            }

            contents.add(commTabDto);

        }

        return contents;
    }

    public List<LangTab> findLangs() {
        List<LangTab> contents = langTabRepository.findAll();

        return contents;
    }

    public void inserting() {
        List<Content> contents = new ArrayList<>();
        Content c1 = new Content();
        c1.setTabPk(1);
        c1.setTitle("GENE");
        c1.setChapter(1);
        c1.setVerse(1);
        c1.setObject("QHHHH");
        c1.setLangId("KOR");
        c1.setVsId("VS01");

        contents.add(c1);

        Content c2 = new Content();
        c2.setTabPk(2);
        c2.setTitle("GENE");
        c2.setChapter(1);
        c2.setVerse(2);
        c2.setObject("QWERTY");
        c2.setLangId("KOR");
        c2.setVsId("VS01");

        contents.add(c2);

        for(Content c : contents){

            contentRepository.save(c);
        }

    }

    public void inserterV1(){

    }

    public void inserterV2(){

    }

    public void inserterV3(){

    }

    public List<ContentMainDto> findMain(){
        List<ContentMainDto> dtos = contentRepository.findMain().stream()
                .map(arr -> new ContentMainDto(
                        (String) arr[0],
                        (String) arr[1],
                        ((Number) arr[2]).intValue()
                )).collect(Collectors.toList());

        return dtos;
    }

    public List<ContentTitleDto> findChapters( String titleNum ){
        List<ContentTitleDto> dtos = contentRepository.getChapters(titleNum).stream()
                .map(arr -> new ContentTitleDto(
                        (String) arr[0],
                        (String) arr[1],
                        ((Number) arr[2]).intValue(),
                        (String) arr[3]
                )).collect(Collectors.toList());

        return dtos;

    }

    public List<ContentChapterDto> findVerses( String titleNum, int chapterNum ){

        List<ContentChapterDto> dtos = contentRepository.getVerses(titleNum,chapterNum).stream()
                .map(arr -> new ContentChapterDto(
                        (String) arr[0],
                        (String) arr[1],
                        ((Number) arr[2]).intValue(),
                        (String) arr[3],
                        ((Number) arr[4]).intValue(),
                        (String) arr[5],
                        (String) arr[6]
                )).collect(Collectors.toList());

        return dtos;
    }

    public String findSpecific( String titleNum, int chapterNum, int verseNum ){
        return contentRepository.getSpecific(titleNum, chapterNum, verseNum);
    }

    public List<ContentFromToResponseDto> postFromTo(ContentFromToRequestDto contentFromToRequestDto){

        // 조정
        int frOrd = contentRepository.getTitleOrd(contentFromToRequestDto.getFromTitleNum());
        int toOrd = contentRepository.getTitleOrd(contentFromToRequestDto.getToTitleNum());

        if(frOrd > toOrd){
            String tempTitle = "";
            int tempChapter = 0;
            int tempVerse = 0;

            tempTitle = contentFromToRequestDto.getFromTitleNum();
            tempChapter = contentFromToRequestDto.getFromChapterNum();
            tempVerse = contentFromToRequestDto.getFromVerseNum();

            contentFromToRequestDto.setFromTitleNum(contentFromToRequestDto.getToTitleNum());
            contentFromToRequestDto.setFromChapterNum(contentFromToRequestDto.getToChapterNum());
            contentFromToRequestDto.setFromVerseNum(contentFromToRequestDto.getToVerseNum());

            contentFromToRequestDto.setToTitleNum(tempTitle);
            contentFromToRequestDto.setToChapterNum(tempChapter);
            contentFromToRequestDto.setToVerseNum(tempVerse);
        }

        List<ContentMetaContentDto> contentMetaContentDtos = contentRepository.getMetaContent().stream()
                .map(arr -> new ContentMetaContentDto(
                        ((Number) arr[0]).intValue(),
                        (String) arr[1],
                        (String) arr[2],
                        ((Number) arr[3]).intValue(),
                        (String) arr[4],
                        ((Number) arr[5]).intValue(),
                        (String) arr[6],
                        (String) arr[7]
                )).collect(Collectors.toList());

        List<ContentFromToResponseDto> contentFromToResponseDtos = new ArrayList<>();

        int inputFlag = 0;

        for(ContentMetaContentDto c : contentMetaContentDtos){

            if(c.getTitle().equals(contentFromToRequestDto.fromTitleNum)
                    && c.getChapter() == contentFromToRequestDto.getFromChapterNum()
                    && c.getVerse() == contentFromToRequestDto.getFromVerseNum() ){
                inputFlag = 1; // FROM 시작점.
            }

            if(inputFlag == 1){
                ContentFromToResponseDto contentFromToResponseDto = new ContentFromToResponseDto();
                contentFromToResponseDto.setTitle(c.getTitle());
                contentFromToResponseDto.setTitleNm(c.getTitleNm());
                contentFromToResponseDto.setChapter(c.getChapter());
                contentFromToResponseDto.setChapterNm(c.getChapterNm());
                contentFromToResponseDto.setVerse(c.getVerse());
                contentFromToResponseDto.setVerseNm(c.getVerseNm());
                contentFromToResponseDto.setObject(c.getObject());

                contentFromToResponseDtos.add(contentFromToResponseDto);
            }

            if(c.getTitle().equals(contentFromToRequestDto.toTitleNum)
                    && c.getChapter() == contentFromToRequestDto.getToChapterNum()
                    && c.getVerse() == contentFromToRequestDto.getToVerseNum() ){
                inputFlag = 0;  // To 오면 끊음.
                break;
            }

        }

        return contentFromToResponseDtos;

    }

    public List<ContentKeyWordDto> getKeyWord(String keyWord){

        List<ContentKeyWordDto> dtos = contentRepository.getKeyWord(keyWord).stream()
                .map(arr -> new ContentKeyWordDto(
                        (String) arr[0],
                        (String) arr[1],
                        ((Number) arr[2]).intValue(),
                        (String) arr[3],
                        ((Number) arr[4]).intValue(),
                        (String) arr[5],
                        (String) arr[6]
                )).collect(Collectors.toList());

        return dtos;
    }

}
