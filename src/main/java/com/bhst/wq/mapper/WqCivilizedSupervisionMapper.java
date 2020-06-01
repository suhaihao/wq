package com.bhst.wq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bhst.wq.entity.WqCivilizedSupervision;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface WqCivilizedSupervisionMapper extends BaseMapper<WqCivilizedSupervision> {

    @Update("update wq_civilized_supervision set dislikes=dislikes+#{likes} where id =#{id}")
    Integer addLikes(@Param("likes") Integer likes, @Param("id") Integer id);
}
