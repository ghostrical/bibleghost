package bibleghost.bibleghost.comm;

import bibleghost.bibleghost.content.Content;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LangTab {
    @Id
    @Column(name = "lang_id")
    private String langId;

    @Column(name = "lang_nm")
    private String langNm;

}
