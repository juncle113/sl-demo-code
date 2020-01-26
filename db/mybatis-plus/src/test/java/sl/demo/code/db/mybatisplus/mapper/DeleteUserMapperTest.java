package sl.demo.code.db.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import sl.demo.code.db.mybatisplus.entity.User;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DeleteUserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void deleteById() {
        int rowCount = userMapper.deleteById(1L);
        System.out.println("影响记录数：" + rowCount);
    }

    @Test
    public void deleteBatchIds() {
        int rowCount = userMapper.deleteBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println("影响记录数：" + rowCount);
    }

    @Test
    public void deleteByMap() {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("name", "Jack");
        whereMap.put("age", "20");

        Integer rowCount = userMapper.deleteByMap(whereMap);
        System.out.println("影响记录数：" + rowCount);
    }

    @Test
    public void deleteByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Jack");

        Integer rowCount = userMapper.delete(queryWrapper);
        System.out.println("影响记录数：" + rowCount);
    }


    @Test
    public void deleteByLambda() {
        LambdaQueryWrapper<User> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.eq(User::getName, "Jack");

        List<User> userList = userMapper.selectList(lambdaWrapper);
        userList.forEach(System.out::println);
    }
}
