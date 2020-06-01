package com.bhst.wq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户")
@TableName("wq_user")
public class WqUser implements UserDetails, Serializable {

    @ApiModelProperty("唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("昵称")
    @TableField(value = "nickname")
    private String nickname;

    @ApiModelProperty("姓名")
    @TableField(value = "fullname")
    private String fullname;

    @ApiModelProperty("邮箱")
    @TableField(value = "email")
    private String email;

    @ApiModelProperty("手机号")
    @TableField(value = "phone")
    private String phone;

    @ApiModelProperty("密码")
    @TableField(value = "password")
    private String password;

    @ApiModelProperty("身份证")
    @TableField(value = "id_number")
    private Integer idNumber;

    @ApiModelProperty("微信")
    @TableField(value = "chat")
    private String chat;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("生日")
    @TableField(value = "born_date")
    private LocalDateTime bornDate;

    @ApiModelProperty("性别")
    @TableField(value = "sex")
    private String sex;

    @ApiModelProperty("等级")
    @TableField(value = "grade")
    private String grade;

    @ApiModelProperty("地址")
    @TableField(value = "address")
    private String address;

    @ApiModelProperty("头像图片")
    @TableField(value = "head_img")
    private String headImg;

    @ApiModelProperty("创建者")
    @TableField(value = "create_by")
    private String createBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("修改时间")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改者")
    @TableField(value = "update_by")
    private Integer updateBy;

    @ApiModelProperty("积分")
    @TableField(value = "integral")
    private Integer integral;

    @ApiModelProperty("队伍")
    @TableField(value = "team")
    private String team;

    @ApiModelProperty("政治面貌")
    @TableField(value = "political_outlook")
    private String politicalOutlook;

    @ApiModelProperty("工作单位")
    @TableField(value = "work_unit")
    private String workUnit;

    @ApiModelProperty("职业")
    @TableField(value = "occupation")
    private String occupation;

    @ApiModelProperty("学历")
    @TableField(value = "highest_education")
    private String highestEducation;

    @ApiModelProperty("是否培训过")
    @TableField(value = "isTrain")
    private Integer isTrain;

    @ApiModelProperty("技能")
    @TableField(value = "service_skills")
    private String serviceSkills;

    @ApiModelProperty("民族")
    @TableField(value = "nation")
    private String nation;

    @ApiModelProperty("服务类型")
    @TableField(value = "service_type")
    private String serviceType;

    @ApiModelProperty("服务时长")
    @TableField(value = "service_duration")
    private String serviceDuration;

    @ApiModelProperty("志愿者编号")
    @TableField(value = "volunter_id")
    private String volunterId;

    @ApiModelProperty("级别")
    @TableField(value = "level")
    private Integer level;


    @ApiModelProperty("权限集合")
    @TableField(exist = false)
    private Set<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        if (!StringUtils.isEmpty(phone)) {
            return phone;
        } else {
            return volunterId;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
