package com.bhst.wq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bhst.wq.entity.WqCivilizedClassroom;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface WqCivilizedClassroomMapper extends BaseMapper<WqCivilizedClassroom> {

    @Update("update wq_civilized_classroom set likes=likes+#{likes} where id =#{id}")
    Integer addLikes(@Param("likes") Integer likes, @Param("id") Integer id);
}
