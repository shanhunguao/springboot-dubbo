package mapper;

import java.util.List;
import model.Test;

public interface TestMapper{
    int insert(Test record);

    List<Test> selectAll();
}