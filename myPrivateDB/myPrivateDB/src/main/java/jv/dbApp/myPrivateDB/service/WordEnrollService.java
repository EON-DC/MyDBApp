package jv.dbApp.myPrivateDB.service;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.repository.WordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordEnrollService {

    private final WordJpaRepository wordJpaRepository;
    private String path = "src/main/resources/csvData.csv";
    public void enrollmentData(){
        String line = "";
        List<Word> words = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null) {

                ArrayList<String> temper = new ArrayList<>();
                String damper = "";
                String[] splitWithBig = line.split("\"");
                if (splitWithBig.length != 1 && splitWithBig.length != 3) {
                    System.out.println("splitWithBig[" + splitWithBig.length + "] = "
                            + Arrays.stream(splitWithBig).map(s -> "__" + s + "__").toList());
                }
                if (splitWithBig.length == 1) {
                    String[] elements = splitWithBig[0].split(",");
                    temper.addAll(Arrays.asList(elements));
                    if (temper.size() == 3) {
                        temper.add("null");
                    }
                } else {
                    /**
                     splitWithBig[0] : {{concept}},"
                     splitWithBig[1] : {{meaning}}
                     splitWithBig[2] : ,{{category}},{{fullName}}
                     **/
                    String concept = splitWithBig[0].substring(0, splitWithBig[0].length() - 1);
                    temper.add(concept);
                    temper.add(splitWithBig[1]);
                    String[] categoryAndFullName = splitWithBig[2].split(",");
                    temper.add(categoryAndFullName[1]);
                    if (categoryAndFullName.length == 2) {
                        temper.add("null");
                    } else {
                        temper.add(categoryAndFullName[2]);
                    }
                }


                System.out.println("temper[" + temper.size() + "] = " + temper.toString());
                // [0] : concept, [1]: meaning, [2]: category, [3]: fullName
                Word word = new Word();
                word.setConcept(temper.get(0));
                word.setMeaning(temper.get(1));
                word.setFullName(temper.get(3));
                word.setCategory(temper.get(2));
                words.add(word);
            }
        } catch (Exception e) {
            throw new RuntimeException("cannot read file" + e);
        }finally {
            wordJpaRepository.saveAllAndFlush(words);
        }

    }

}
