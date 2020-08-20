package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Toby.Li on 2020/8/19.
 */
@NoArgsConstructor //无参构造
@AllArgsConstructor //有参数构造
@Data //set/get
public class Result {

    private boolean flag; //是否成功
    private Integer code; //返回码
    private String message; //返回信息
    private Object data; //返回数据
}
