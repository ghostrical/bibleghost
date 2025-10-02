package bibleghost.bibleghost.content;

import bibleghost.bibleghost.comm.CommTab;
import bibleghost.bibleghost.comm.CommTabDto;
import bibleghost.bibleghost.comm.LangTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final static String CONTENT_DEFAULT_URL = "/content";

    @Autowired
    private final ContentService contentService;

    @Autowired
    private final ContentMapper contentMapper;

    public ContentController(ContentService contentService, ContentMapper contentMapper){
        this.contentService = contentService;
        this.contentMapper = contentMapper;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> contentPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/main.html");
        String html = Files.readString(htmlFile.getFile().toPath());
        return ResponseEntity.ok(html);
    }

    @GetMapping(value = "/specific", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> specificPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/specific.html");
        String html = Files.readString(htmlFile.getFile().toPath());
        return ResponseEntity.ok(html);
    }

    @GetMapping(value = "/FromTo", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> fromToPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/fromto.html");
        String html = Files.readString(htmlFile.getFile().toPath());
        return ResponseEntity.ok(html);
    }

    @GetMapping(value = "/KeyWord", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> keyWordPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/keyword.html");
        String html = Files.readString(htmlFile.getFile().toPath());
        return ResponseEntity.ok(html);
    }

    @GetMapping(value = "/thisTitle/{title}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> chaptersPage(@PathVariable("title") String title) throws IOException {
        // title 값은 필요에 따라 HTML 렌더링에 활용 가능
        ClassPathResource htmlFile = new ClassPathResource("static/chapters.html");
        String html = Files.readString(htmlFile.getFile().toPath());
        return ResponseEntity.ok(html);
    }

    @GetMapping(value = "/thisTitle/{title}/thisChapter/{chapter}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> versesPage(@PathVariable("title") String title, @PathVariable("chapter") int chapter) throws IOException {

        ClassPathResource htmlFile = new ClassPathResource("static/verses.html");
        String html = Files.readString(htmlFile.getFile().toPath());
        return ResponseEntity.ok(html);
    }

    @GetMapping("/comm")
    public ResponseEntity getComms() {
        List<CommTabDto> contents = contentService.findComms();

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @GetMapping("/lang")
    public ResponseEntity getLangs() {
        List<LangTab> contents = contentService.findLangs();

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    //BASIC
    @GetMapping("/{tab-pk}")
    public ResponseEntity getContent(@PathVariable("tab-pk") int tabPk){
        Content response = contentService.findContent(tabPk);

        return new ResponseEntity<>(contentMapper.ContentToContentResponseDto(response), HttpStatus.OK);
    }

    //BASIC
    @PostMapping("/inserter")
    public ResponseEntity getInserter(){

        contentService.inserting();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //BASIC
    @PostMapping("/inserterv1")
    public ResponseEntity getInserterv1(){

        contentService.inserterV1();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/inserterv2")
    public ResponseEntity getInserterv2(){

        contentService.inserterV2();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/inserterv3")
    public ResponseEntity getInserterv3(){

        contentService.inserterV3();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/title")
    public ResponseEntity getMain() {
        List<ContentMainDto> contents = contentService.findMain();

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @GetMapping("/title/{title-num}")
    public ResponseEntity getTitle(@PathVariable("title-num") String titleNum) {
        List<ContentTitleDto> contents = contentService.findChapters(titleNum);

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @GetMapping("/title/{title-num}/chapter/{chapter-num}")
    public ResponseEntity getChapter(@PathVariable("title-num") String titleNum, @PathVariable("chapter-num") int chapterNum ) {
        List<ContentChapterDto> contents = contentService.findVerses(titleNum, chapterNum);

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @GetMapping("/specific/{title-num}/{chapter-num}/{verse-num}")
    public ResponseEntity getSpecific(@PathVariable("title-num") String titleNum, @PathVariable("chapter-num") int chapterNum
                                    , @PathVariable("verse-num") int verseNum ) {
        String contents = contentService.findSpecific(titleNum, chapterNum, verseNum);

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }


    @PostMapping("/fromTo")
    public ResponseEntity postFromTo(@RequestBody ContentFromToRequestDto contentFromToRequestDto){
        List<ContentFromToResponseDto> contents = contentService.postFromTo(contentFromToRequestDto);

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @GetMapping("/keyWord/{keyword}")
    public ResponseEntity getKeyWord(@PathVariable("keyword") String keyword){
        List<ContentKeyWordDto> contents = contentService.getKeyWord(keyword);

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

}
