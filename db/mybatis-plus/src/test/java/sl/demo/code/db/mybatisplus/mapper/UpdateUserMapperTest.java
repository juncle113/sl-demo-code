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
import org.springframework.util.StringUtils;
import sl.demo.code.db.mybatisplus.entity.User;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UpdateUserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void updateById() {
        User user = new User();
        user.setId(1L);
        user.setName("Sunli");
        user.setAge(36);

        int rowCount = userMapper.updateById(user);
        System.out.println("影响记录数：" + rowCount);
    }

    @Test
    public void updateByWrapper() {
        User user = new User();
        user.setName("Sunli");
        user.setAge(36);

        UpdateWrapper<User> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("name", "Jack");

        int rowCount = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数：" + rowCount);
    }

    @Test
    public void updateByWrapperSet() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("name", "Jack").set("age", 30);

        int rowCount = userMapper.update(null, updateWrapper);
        System.out.println("影响记录数：" + rowCount);
    }

    @Test
    public void updateByLambda() {
        LambdaUpdateWrapper<User> lambdaWrapper = new LambdaUpdateWrapper<>();
        lambdaWrapper.eq(User::getName, "Jack").set(User::getAge, 30);

        int rowCount = userMapper.update(null, lambdaWrapper);
        System.out.println("影响记录数：" + rowCount);
    }

    @Test
    public void updateByLambdaChain() {
        boolean result = new LambdaUpdateChainWrapper<User>(userMapper)
                .eq(User::getName, "Jack").set(User::getAge, 30).update();
        System.out.println("更新结果：" + result);
    }
}
