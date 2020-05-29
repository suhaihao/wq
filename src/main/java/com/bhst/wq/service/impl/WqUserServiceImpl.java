package com.bhst.wq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhst.wq.entity.WqUser;
import com.bhst.wq.mapper.WqUserMapper;
import com.bhst.wq.request.WqUserDetailDelRequest;
import com.bhst.wq.request.WqUserPageListRequest;
import com.bhst.wq.response.WqUserResponse;
import com.bhst.wq.service.WqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WqUserServiceImpl extends ServiceImpl<WqUserMapper, WqUser> implements WqUserService, UserDetailsService {

    private final WqUserMapper wqUserMapper;


    @Autowired
    public WqUserServiceImpl(WqUserMapper wqUserMapper) {
        this.wqUserMapper = wqUserMapper;
    }

    @Override
    public IPage<WqUser> getPageList(WqUserPageListRequest request) {
        Page<WqUser> page = new Page<>(request.getPageIndex(), request.getPageSize());
        QueryWrapper<WqUser> queryWrapperUser = new QueryWrapper();
        queryWrapperUser.orderByDesc("create_time");
        return wqUserMapper.selectPage(page, queryWrapperUser);
    }

    @Override
    public WqUser getById(WqUserDetailDelRequest request) {
        return wqUserMapper.selectById(request.getId());
    }

    @Override
    public Boolean delById(WqUserDetailDelRequest request) {
        return wqUserMapper.deleteById(request.getId()) > 0;
    }

    @Override
    public IPage<WqUserResponse> getRankingPageList(WqUserPageListRequest request) {
        Page<WqUserResponse> page = new Page<>(request.getPageIndex(), request.getPageSize());
        return wqUserMapper.getRankingByPageList(page);
    }

    @Override
    public WqUser loginByUserName(String userName) {
        QueryWrapper<WqUser> queryWrapper = new QueryWrapper<>();
        if (userName.length() > 11) {
            queryWrapper.eq("volunter_id", userName);
        } else {
            queryWrapper.eq("phone", userName);
        }
        WqUser wqUser = wqUserMapper.selectOne(queryWrapper);
        return wqUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<WqUser> queryWrapper = new QueryWrapper<>();
        if (username.length() > 11) {
            queryWrapper.eq("volunter_id", username);
        } else {
            queryWrapper.eq("phone", username);
        }
        WqUser wqUser = wqUserMapper.selectOne(queryWrapper);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String ps = encoder.encode("123");
        wqUser.setPassword(ps);
        Set authoritiesSet = new HashSet();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authoritiesSet.add(authority);
        wqUser.setAuthorities(authoritiesSet);
        return wqUser;
    }
}
