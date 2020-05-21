package com.yicheng.tourism.service;

import com.github.pagehelper.PageInfo;
import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.dto.order.req.OrderCarReq;
import com.yicheng.tourism.dto.role.req.AssignRoleReq;
import com.yicheng.tourism.dto.user.req.UpdateUserInfoReq;
import com.yicheng.tourism.dto.user.req.UserQryConditionReq;
import com.yicheng.tourism.dto.user.req.UserRegisterOrLoginReq;
import com.yicheng.tourism.dto.user.resp.UserQryResp;
import com.yicheng.tourism.entity.Permission;
import com.yicheng.tourism.entity.Role;
import com.yicheng.tourism.entity.User;
import com.yicheng.tourism.model.PageParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    List<Role> getRoleList(String userId);

    /**用户注册
     * @param req
     * @param request
     * @return
     */
    BaseResponse<String> register(UserRegisterOrLoginReq req,HttpServletRequest request);
    /**
     * 用户登录
     * @param req 用户登录请求参数
     * @param request
     * @return
     */
    BaseResponse<UserQryResp> login(UserRegisterOrLoginReq req, HttpServletRequest request);

    /**获取用户详情
     * @param serialCode
     * @return
     */
    BaseResponse<User> getDetail(String serialCode);

    /**编辑/修改用户信息
     * @param req
     * @return
     */
    BaseResponse<String> edit(UpdateUserInfoReq req,HttpServletRequest request);

    /**
     * 按条件进行用户信息查询
     * @param req 请求参数
     *
     * @return 数据列表
     */
    BaseResponse<PageInfo<User>> qryByCondition(UserQryConditionReq req);

    /**为用户分配角色
     * @param roleReq
     * @return
     */
    BaseResponse<String> assignRole(List<AssignRoleReq> roleReq,HttpServletRequest request);

    /**验证用户是否有对应api的访问权限
     * @return
     */
    BaseResponse<User> verification(HttpServletRequest request);


    /**根据token获取登录信息
     * @param request
     * @return
     */
    User getToken(HttpServletRequest request);

    Object queryById(UpdateUserInfoReq req);

    Object pasdVer(OrderCarReq req);
}
