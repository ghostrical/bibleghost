package bibleghost.bibleghost.freeBoard;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer> {

    @Query(value = "SELECT MAX(BOARD_PK) FROM free_board", nativeQuery =true)
    Integer getMaxBoardPk();

    @Query(value = "SELECT COUNT(BOARD_PK) FROM free_board", nativeQuery =true)
    Integer getBoardPkCount();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM free_board WHERE BOARD_PK = :boardPk ", nativeQuery = true)
    void deleteNative(@Param("boardPk") int boardPk );

}
