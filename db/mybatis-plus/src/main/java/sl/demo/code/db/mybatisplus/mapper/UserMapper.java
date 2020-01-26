package sl.demo.code.db.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sl.demo.code.db.mybatisplus.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    // 自定义sql
    @Select("select * from user ${ew.customSqlSegment}")
    List<User> mySelect(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    // 自定义sql
    List<User> mySelectByXml(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    // 自定义翻页(适用于多表查询的翻页)
    IPage<User> selectWithUserPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

}
