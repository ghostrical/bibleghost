package bibleghost.bibleghost.content;

import bibleghost.bibleghost.comm.CommTab;
import bibleghost.bibleghost.comm.LangTab;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Content {
    @Id
    @Column(name = "tab_pk")
    private int tabPk;

    @Column(name = "title")
    private String title;

    @Transient
    private String titleNm;

    @Column(name = "chapter")
    private int chapter;

    @Column(name = "verse")
    private int verse;

    @Column(name = "object")
    private String object;

    @Column(name = "lang_id")
    private String langId;

    @Transient
    private String langNm;

    @Column(name = "vs_id")
    private String vsId;

    @Transient
    private String vsNm;

}
