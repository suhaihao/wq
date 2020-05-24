package com.bhst.wq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhst.wq.entity.WqTeam;
import com.bhst.wq.response.WqTeamResponse;
import org.apache.ibatis.annotations.Select;

public interface WqTeamMapper extends BaseMapper<WqTeam> {
    /**
     * 分页获取排行信息
     *
     * @param page
     * @return
     */
    @Select(value = "select (@r :=@r + 1) as rowNum,s.* from wq_team s,(select @r := 0) r order by s.service_duration desc")
    IPage<WqTeamResponse> getRankingByPageList(Page page);
}
