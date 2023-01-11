package jv.dbApp.myPrivateDB.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long id;

    private String concept;

    private String meaning;

    public Word(String concept, String meaning) {
        this.concept = concept;
        this.meaning = meaning;
    }

    public void update(String concept, String meaning) {
        this.concept = concept;
        this.meaning = meaning;
    }
}
