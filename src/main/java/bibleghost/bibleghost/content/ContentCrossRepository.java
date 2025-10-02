package bibleghost.bibleghost.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContentCrossRepository extends JpaRepository<ContentCross,Integer> {
    Optional<Content> findAllByTabPk(int tabPk);

    @Query(value = "SELECT MAX(TAB_PK) FROM CONTENT_CROSS",nativeQuery = true)
    Integer findMaxTabPk();

}
