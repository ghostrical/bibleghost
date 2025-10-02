package bibleghost.bibleghost.freeBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/freeBoard")
public class FreeBoardController {
    private final static String CONTENT_DEFAULT_URL = "/freeBoard";

     @Autowired
     private final FreeBoardService freeBoardService;

     @Autowired
     private final FreeBoardMapper freeBoardMapper;

     public FreeBoardController(FreeBoardService freeBoardService, FreeBoardMapper freeBoardMapper){
       this.freeBoardService = freeBoardService;
       this.freeBoardMapper = freeBoardMapper;
     }

    @GetMapping(value = "/board", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> boardMainPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/boardmain.html");
        String html = Files.readString(htmlFile.getFile().toPath());
        return ResponseEntity.ok(html);
    }

    @GetMapping(value = "/insert", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> boardInsertPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/boardinsert.html");
        String html = Files.readString(htmlFile.getFile().toPath());
        return ResponseEntity.ok(html);
    }

    @GetMapping(value = "/update/{boardPk}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> boardUpdatePage(@PathVariable("boardPk") int boardPk) throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/boardupdate.html");
        String html = Files.readString(htmlFile.getFile().toPath());
        return ResponseEntity.ok(html);
    }

    @GetMapping(value = "/boardDetail/{boardPk}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> boardDetailPage(@PathVariable("boardPk") int boardPk) throws IOException {
        // title 값은 필요에 따라 HTML 렌더링에 활용 가능
        ClassPathResource htmlFile = new ClassPathResource("static/boarddetail.html");
        String html = Files.readString(htmlFile.getFile().toPath());
        return ResponseEntity.ok(html);
    }

    @GetMapping
    public ResponseEntity getFreeBoards(){
       List<FreeBoard> freeBoards = freeBoardService.findFreeBoards();

       List<FreeBoardDto> response = freeBoardMapper.FreeBoardsToFreeBoardDtos(freeBoards);

       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{board-pk}")
    public ResponseEntity getFreeBoard(@PathVariable("board-pk") int boardPk){
         FreeBoard freeBoard = freeBoardService.findFreeBoard(boardPk);

         FreeBoardDto response = freeBoardMapper.FreeBoardToFreeBoardDto(freeBoard);

         return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity postFreeBoard(@RequestBody FreeBoardDto freeBoardDto){
         FreeBoard freeBoard = freeBoardService.createFreeBoard(freeBoardMapper.FreeBoardDtoToFreeBoard(freeBoardDto));

         return new ResponseEntity<>(freeBoard, HttpStatus.CREATED);
    }


    @PatchMapping("/{board-pk}")
    public ResponseEntity patchFreeBoard(@PathVariable("board-pk") int boardPk, @RequestBody FreeBoardDto freeBoardDto){
         freeBoardDto.setBoardPk(boardPk);
         FreeBoard freeBoard = freeBoardService.updateFreeBoard(freeBoardMapper.FreeBoardDtoToFreeBoard(freeBoardDto));

         return new ResponseEntity<>(freeBoard, HttpStatus.ACCEPTED);
    }

    // DELETE
    @DeleteMapping("/{board-pk}")
    public ResponseEntity deleteFreeBoard(@PathVariable("board-pk") int boardPk){
         freeBoardService.deleteFreeBoard(boardPk);

         return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
