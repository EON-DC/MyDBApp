package jv.dbApp.myPrivateDB.dto;


import jv.dbApp.myPrivateDB.domain.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class QuizDto {

    private String concept;
    private int answerIndex;
    private Word[] select;

    public QuizDto(Word[] words) {
        this.select = words;
        this.answerIndex = ((int) (Math.random() * 5));
        this.concept = select[answerIndex].getConcept();
    }

    @Override
    public String toString() {
        return "QuizDto{" +
                "concept='" + concept + '\'' +
                ", answerIndex=" + answerIndex +
                ", selected Concepts=" + Arrays.stream(select).map(Word::getConcept).toList() +
                '}';
    }
}
