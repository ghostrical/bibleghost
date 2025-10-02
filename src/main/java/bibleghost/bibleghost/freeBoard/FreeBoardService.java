package bibleghost.bibleghost.freeBoard;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FreeBoardService {

    @Autowired
    private final FreeBoardRepository freeBoardRepository;

    @Autowired
    private final FreeBoardMapper freeBoardMapper;

    public FreeBoardService(FreeBoardRepository freeBoardRepository, FreeBoardMapper freeBoardMapper){
        this.freeBoardRepository = freeBoardRepository;
        this.freeBoardMapper = freeBoardMapper;
    }

    public List<FreeBoard> findFreeBoards () {
        return freeBoardRepository.findAll();
    }

    public FreeBoard findFreeBoard(int boardPk){
        Optional<FreeBoard> optionalFreeBoard = freeBoardRepository.findById(boardPk);
        FreeBoard findFreeBoard = optionalFreeBoard.get();

        return findFreeBoard;
    }

    public FreeBoard createFreeBoard(FreeBoard freeBoard){

        Integer getBoardPkCount = freeBoardRepository.getBoardPkCount();

        if(getBoardPkCount == 0) {
            freeBoard.setBoardPk(0);
        }
        else {

            Integer getMaxBoardPk = freeBoardRepository.getMaxBoardPk();
            freeBoard.setBoardPk(getMaxBoardPk+1);
        }

        if(!freeBoard.getWriter().equals("pubgliveSPECTRE") ){

           return null;
        }

        freeBoard.setWriter("ghost");

        return freeBoardRepository.save(freeBoard);
    }

    public FreeBoard updateFreeBoard(FreeBoard freeBoard){

        if(!freeBoard.getWriter().equals("pubgliveSPECTRE") ){

            return null;
        }

        freeBoard.setWriter("ghost");

        return freeBoardRepository.save(freeBoard);
    }

    public void deleteFreeBoard(int boardPk){

        freeBoardRepository.deleteNative(boardPk);
    }
}
