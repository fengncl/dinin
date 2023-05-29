package com.ttttt.core.controller;

import cn.hutool.core.util.ObjUtil;
import com.ttttt.core.db.dao.CommentRepository;
import com.ttttt.core.db.dao.UserRepository;
import com.ttttt.core.db.entity.User;
import com.ttttt.core.util.R;
import com.ttttt.core.util.Tool;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Tag(name = "用户操作接口")
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserRepository userRepository;

    @Inject
    CommentRepository commentRepository;

    @Operation(summary = "获取单个用户信息")
    @GET
    @Path("/{id}")
    public R<User> getUserById(@PathParam("id") Long id) {
        if (ObjUtil.isEmpty(id)) {
            return R.ok();
        }
        Optional<User> user = userRepository.findById(id.intValue());
        if (user.isEmpty()) {
            return R.error("id不存在");
        }
        return R.ok(user.get());
    }

    @Operation(summary = "获取所有户信息")
    @GET
    @Path("/all")
    public R<List<User>> getUserAll() {
        return R.ok(userRepository.findAll());
    }

    @Operation(summary = "增加用户")
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/")
    public R createUser(User user) {
        if (ObjUtil.isEmpty(user.getName())) {
            return R.error("请填写用户名");
        }
        if (user.getName().length() > 50) {
            return R.error("用户名长度不得大于50");
        }
        if (!Tool.isValidEmail(user.getEmail())) {
            return R.error("邮箱格式错误");
        }
        if (!Tool.isValidPhone(user.getPhone())) {
            return R.error("手机号格式错误");
        }
        User user1 = userRepository.findByEmail(user.getEmail());
        if (user1 != null) {
            return R.error("邮箱已绑定账号");
        }
        User user2 = userRepository.findByPhone(user.getPhone());
        if (user2 != null) {
            return R.error("手机号已绑定账号");
        }
        user.setId(0);
        userRepository.save(user);
        return R.ok();
    }

    @Operation(summary = "根据id删除用户")
    @DELETE
    @Path("/{id}")
    public R deleteUser(@PathParam("id") Integer id) {
        //查询用户
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return R.error("id不存在");
        }
        //删除餐厅的评论
        commentRepository.deleteAllByUId(id);
        //删除用户
        userRepository.deleteById(id);
        return R.ok();
    }

}