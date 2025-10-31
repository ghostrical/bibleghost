package bibleghost.bibleghost.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content,Integer> {
    Optional<Content> findAllByTabPk(int tabPk);

    @Query(value = "SELECT MAX(TAB_PK) FROM content",nativeQuery = true)
    Integer findMaxTabPk();

    @Query(value = "SELECT A.TITLE, (SELECT C.COMM_NM FROM comm_tab C WHERE C.COMM_ID = A.TITLE AND C.LANG_ID = 'KOR') AS TITLE_NM, A.MAX_CHAPTER "
                    +" FROM indexer A"
                    +" ORDER BY A.ORD ", nativeQuery = true)
    List<Object[]> findMain();

    @Query(value = "SELECT B.TITLE "
                    +" , (SELECT C.COMM_NM FROM comm_tab C WHERE C.COMM_ID = B.TITLE AND C.LANG_ID = 'KOR') AS TITLE_NM "
                    +" , B.CHAPTER "
                    +" , CONCAT(B.CHAPTER,'장') AS CHAPTER_NM "
                    +" FROM ( SELECT A.TITLE, A.CHAPTER "
                    +" FROM content A "
                    +" WHERE A.TITLE = :titleNum "
                    +" GROUP BY A.TITLE, A.CHAPTER "
                    +" ORDER BY A.TITLE, A.CHAPTER ) B ", nativeQuery = true)
    List<Object[]> getChapters(@Param("titleNum") String titleNum);

    @Query(value = "SELECT B.TITLE "
                    +" , (SELECT C.COMM_NM FROM COMM_TAB C WHERE C.COMM_ID = B.TITLE AND C.LANG_ID = 'KOR' ) AS TITLE_NM "
                    +" , B.CHAPTER "
                    +" , CONCAT(B.CHAPTER,'장') AS CHAPTER_NM "
                    +" , B.VERSE "
                    +" , CONCAT(B.VERSE,'절') AS VERSE_NM "
                    +" , B.OBJECT "
                    +" FROM ( SELECT A.TITLE, A.CHAPTER, A.VERSE, A.OBJECT "
                    +" FROM content A "
                    +" WHERE A.TITLE = :titleNum "
                    +" AND A.CHAPTER = :chapterNum "
                    +" ORDER BY A.VERSE ) B ", nativeQuery = true)
    List<Object[]> getVerses(@Param("titleNum") String titleNum, @Param("chapterNum") int chapterNum);

    @Query(value = "SELECT OBJECT "
                    +" FROM content "
                    +" WHERE TITLE = :titleNum "
                    +" AND CHAPTER = :chapterNum "
                    +" AND VERSE = :verseNum ", nativeQuery = true)
    String getSpecific(@Param("titleNum") String titleNum, @Param("chapterNum") int chapterNum, @Param("verseNum") int verseNum);

    @Query(value = "SELECT ORD FROM indexer WHERE TITLE = :titleNum ", nativeQuery = true)
    Integer getTitleOrd(@Param("titleNum") String titleNum);

    @Query(value = "SELECT A.ORD, A.TITLE "
                    +" , (SELECT C.COMM_NM FROM comm_tab C WHERE C.COMM_ID = B.TITLE AND C.LANG_ID = 'KOR') AS TITLE_NM "
                    +" , B.CHAPTER "
                    +" , CONCAT(B.CHAPTER,'장') AS CHAPTER_NM "
                    +" , B.VERSE "
                    +" , CONCAT(B.VERSE,'절') AS VERSE_NM "
                    +" , B.OBJECT "
                    +" FROM indexer A, content B "
                    +" WHERE A.TITLE = B.TITLE "
                    +" ORDER BY A.ORD, B.CHAPTER, B.VERSE ", nativeQuery = true)
    List<Object[]> getMetaContent();

    @Query(value = "SELECT A.TITLE "
                    +" , (SELECT C.COMM_NM FROM comm_tab C WHERE C.COMM_ID = B.TITLE AND C.LANG_ID = 'KOR') AS TITLE_NM "
                    +" , A.CHAPTER "
                    +" , CONCAT(A.CHAPTER,'장') AS CHAPTER_NM "
                    +" , A.VERSE "
                    +" , CONCAT(A.VERSE,'절') AS VERSE_NM "
                    +" , A.OBJECT "
                    +" FROM content A, indexer B "
                    +" WHERE A.TITLE = B.TITLE "
                    +" AND A.OBJECT LIKE CONCAT('%', :keyWord, '%') "
                    +" ORDER BY B.ORD, A.CHAPTER, A.VERSE ", nativeQuery = true)
    List<Object[]> getKeyWord(@Param("keyWord") String keyWord);

}
