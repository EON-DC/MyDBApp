package jv.dbApp.myPrivateDB.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "word_id")
    private Long id;

    private String concept;

    @Column(length = 600)
    private String meaning;

    private String category;

    private String fullName;


    public Word(String concept, String meaning) {
        this.concept = concept;
        this.meaning = meaning;
        this.category = null;
        this.fullName = "null";
    }

    public Word(String concept, String meaning, String category) {
        this.concept = concept;
        this.meaning = meaning;
        this.category = category;
        this.fullName = "null";
    }

    public Word(String concept, String meaning, String category, String fullName) {
        this.concept = concept;
        this.meaning = meaning;
        this.category = category;
        this.fullName = fullName;
    }

    public void update(String concept, String meaning, String category, String fullName) {
        this.concept = concept;
        this.meaning = meaning;
        this.category = category;
        this.fullName = fullName;

    }
}
