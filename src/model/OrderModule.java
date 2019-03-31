package model;

import lombok.Getter;
import lombok.Setter;


@Setter@Getter
public class OrderModule {
    private Integer id;
    private Integer user_id;
    private String seller_name;
    private String time;

    private CodeModule codeModule;
}