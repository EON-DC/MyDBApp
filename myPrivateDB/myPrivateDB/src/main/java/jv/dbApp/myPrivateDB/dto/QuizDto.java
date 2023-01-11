package jv.dbApp.myPrivateDB.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDto {

    private String concept;
    private int answerIndex;
    private String[] questionArray;
}
