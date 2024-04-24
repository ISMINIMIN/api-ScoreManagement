package minzdev.api.exception.restriction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputRestriction {

    private int minGrade;
    private int maxGrade;

}
