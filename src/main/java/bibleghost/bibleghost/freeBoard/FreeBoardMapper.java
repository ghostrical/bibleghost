package bibleghost.bibleghost.freeBoard;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FreeBoardMapper {

    public FreeBoard FreeBoardDtoToFreeBoard(FreeBoardDto freeBoardDto){
        FreeBoard freeBoard = new FreeBoard();

        if(freeBoardDto != null){
            freeBoard.setBoardPk(freeBoardDto.getBoardPk());
            freeBoard.setTitle(freeBoardDto.getTitle());
            freeBoard.setWriter(freeBoardDto.getWriter());
            freeBoard.setSource(freeBoardDto.getSource());
            freeBoard.setCreateDt(freeBoardDto.getCreateDt());
            freeBoard.setUpdateDt(freeBoardDto.getUpdateDt());
        }

        return freeBoard;
    }

    public FreeBoardDto FreeBoardToFreeBoardDto(FreeBoard freeBoard){
        FreeBoardDto freeBoardDto = new FreeBoardDto();

        if(freeBoard != null){
            freeBoardDto.setBoardPk(freeBoard.getBoardPk());
            freeBoardDto.setTitle(freeBoard.getTitle());
            freeBoardDto.setWriter(freeBoard.getWriter());
            freeBoardDto.setSource(freeBoard.getSource());
            freeBoardDto.setCreateDt(freeBoard.getCreateDt());
            freeBoardDto.setUpdateDt(freeBoard.getUpdateDt());
        }

        return freeBoardDto;
    }

    public List<FreeBoardDto> FreeBoardsToFreeBoardDtos(List<FreeBoard> freeBoards){
        List<FreeBoardDto> freeBoardDtos = new ArrayList<>();

        if(freeBoards.size() > 0){

            for(FreeBoard f : freeBoards){
                FreeBoardDto freeBoardDto = new FreeBoardDto();

                freeBoardDto.setBoardPk(f.getBoardPk());
                freeBoardDto.setTitle(f.getTitle());
                freeBoardDto.setWriter(f.getWriter());
                freeBoardDto.setSource(f.getSource());
                freeBoardDto.setCreateDt(f.getCreateDt());
                freeBoardDto.setUpdateDt(f.getUpdateDt());

                freeBoardDtos.add(freeBoardDto);
            }
        }

        return freeBoardDtos;
    }

}
