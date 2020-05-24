package com.bhst.wq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.response.WqTeamResponse;
import com.bhst.wq.response.WqUserResponse;
import org.apache.ibatis.annotations.Select;

public interface WqUserMapper extends BaseMapper<WqUser> {
    /**
     * 分页获取排行信息
     *
     * @param page
     * @return
     */
    @Select(value = "select (@r :=@r + 1) as rowNum,s.* from wq_user s,(select @r := 0) r order by s.integral desc")
    IPage<WqUserResponse> getRankingByPageList(Page page);
}
