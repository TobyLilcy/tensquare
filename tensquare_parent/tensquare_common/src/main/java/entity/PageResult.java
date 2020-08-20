package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Toby.Li on 2020/8/19.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageResult<T> {

    private Long total;
    private List<T> rows;
}
