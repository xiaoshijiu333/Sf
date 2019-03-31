package model;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class LikeCode {
    private Integer id;
    private Integer user_id;
    private String liketime;
    private CodeModule codeModule;
}
