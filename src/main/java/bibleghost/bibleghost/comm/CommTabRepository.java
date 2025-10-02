package bibleghost.bibleghost.comm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommTabRepository extends JpaRepository<CommTab, CommTabPK> {
    @Query(value = "SELECT * FROM COMM_TAB WHERE COMM_ID = :commId AND LANG_ID = :langId", nativeQuery = true)
    CommTab findCommNmByCodeAndLangId(@Param("commId") String commId, @Param("langId") String langId);

    @Query(value = "SELECT * FROM COMM_TAB WHERE LANG_ID = 'KOR'", nativeQuery = true)
    List<CommTab> findAllByKor();
}
