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
public class SelectUserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void selectList() {
        List<User> userList = userMapper.selectList(null);
        assertEquals(userList.size(), 6);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }


    @Test
    public void selectByIds() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);

        List<User> userList = userMapper.selectBatchIds(ids);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("name", "Jack");
        whereMap.put("age", "20");
        whereMap.put("parent_id", "1");

        List<User> userList = userMapper.selectByMap(whereMap);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name")
                .select(User.class,
                        tableFieldInfo -> !tableFieldInfo.getColumn().equals("create_time")
                                && !tableFieldInfo.getColumn().equals("parent_id"))
                .eq("name", "Jack").isNotNull("email")
                .apply("parsedatetime(create_time, 'yyyy-MM-dd hh:mm:ss')={0}", "2020-01-01 10:20:30")
                .inSql("id", "select parent_id from user where name = 'Jack'")
                .and(queryWrapper2 -> queryWrapper2.like("name", "ac").lt("age", 40)
                        .or().likeLeft("name", "om").between("age", 20, 40))
                .last("limit 1")
                .orderByDesc("age");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperCondition() {
        String name = "Jack";
        Integer age = null;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(name), "name", name)
                .eq(!StringUtils.isEmpty(age), "age", age);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperEntity() {
        User user = new User();
        user.setName("Jack");
        user.setAge(20);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        queryWrapper.eq("id", 2);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperAllEq() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Jack");
        params.put("age", null);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(params, false);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) as avg_age", "min(age) as min_age", "max(age) as max_age")
                .groupBy("parent_id").having("sum(age)<{0}", 500);

        List<Map<String, Object>> userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        List<Object> userList = userMapper.selectObjs(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("总记录数：" + count);
    }

    @Test
    public void selectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Jack");

        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void selectByLambda() {
        LambdaQueryWrapper<User> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.like(User::getName, "ac").lt(User::getAge, 20);

        List<User> userList = userMapper.selectList(lambdaWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByLambda2() {
        List<User> userList = new LambdaQueryChainWrapper<User>(userMapper).like(User::getName, "ck").list();
        userList.forEach(System.out::println);
    }

    @Test
    public void mySelect() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "ac");

        List<User> userList = userMapper.mySelect(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void mySelectByXml() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "ac");

        List<User> userList = userMapper.mySelectByXml(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectWithPage() {
        // 查询翻页信息和数据
        Page<User> page = new Page<>(1, 2);
        // 不查询翻页信息，只查询数据
        // Page<User> page = new Page<>(1, 2, false);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "ac");

        IPage<User> userPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("总页数：" + userPage.getPages());
        System.out.println("总记录数：" + userPage.getTotal());

        List<User> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectWithUserPage() {
        Page<User> page = new Page<>(1, 2);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "ac");

        IPage<User> userPage = userMapper.selectWithUserPage(page, queryWrapper);
        System.out.println("总页数：" + userPage.getPages());
        System.out.println("总记录数：" + userPage.getTotal());

        List<User> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }
}
