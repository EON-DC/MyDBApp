package jv.dbApp.myPrivateDB.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "word_id")
    private Long id;

    private String concept;

    private String meaning;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String fullName;


    public Word(String concept, String meaning) {
        this.concept = concept;
        this.meaning = meaning;
    }

    public void update(String concept, String meaning) {
        this.concept = concept;
        this.meaning = meaning;
    }
}
