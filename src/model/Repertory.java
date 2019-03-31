package model;


import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Repertory{
    private Integer id;
    private Integer user_id;
    private String name;
    private String info;
    private String status;
    private String create_date;
    private String modify_date;
    private Integer file_num;

}