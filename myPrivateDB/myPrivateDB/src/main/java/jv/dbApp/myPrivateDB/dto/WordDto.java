package jv.dbApp.myPrivateDB.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WordDto {

    private String concept;
    private String meaning;
    private String category;
    private String fullName;



}
