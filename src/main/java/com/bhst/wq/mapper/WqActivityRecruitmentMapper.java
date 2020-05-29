package com.bhst.wq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bhst.wq.entity.WqActivityRecruitment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface WqActivityRecruitmentMapper extends BaseMapper<WqActivityRecruitment> {

    @Update("update wq_activity_recruitment set likes=likes+#{likes} where id =#{id}")
    Integer addLikes(@Param("likes") Integer likes, @Param("id") Integer id);
}
