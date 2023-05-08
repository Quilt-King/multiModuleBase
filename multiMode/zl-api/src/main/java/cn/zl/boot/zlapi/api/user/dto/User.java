package cn.zl.boot.zlapi.api.user.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zcy
 * @version 1.0.0
 * @date 2023/5/8 9:52
 * @description:
 */
@Data
@TableName("user")
public class User {
    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
