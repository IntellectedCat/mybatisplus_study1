package net.togogo.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "last_name")
    private String lastname;
    private String email;
    private Integer age;
}
