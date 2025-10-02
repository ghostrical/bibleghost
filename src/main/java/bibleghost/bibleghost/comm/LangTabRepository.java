package bibleghost.bibleghost.comm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LangTabRepository extends JpaRepository<LangTab, String> {
    LangTab findLangNmByLangId(String langId);
}
