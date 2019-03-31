package model;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Message {
    private Integer id;
    private User fromUser;
    private User toUser;
    private String time;
    private String context;
    private Integer status;

}
