package sl.demo.code.db.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
//@TableName("user")
public class User {
    @TableId
    private Long id;
//  selectByWrapperEntity
//    @TableField(condition = SqlCondition.LIKE)
    private String name;
//  selectByWrapperEntity
//    @TableField(condition = "%s&lt;#{%s}")
    private Integer age;
    private String email;
//    @TableField("parent_id")
    private Long parentId;
    private LocalDateTime createTime;
    @TableField(exist = false)
    private String remark;
}
