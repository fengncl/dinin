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

@Tag(name = "User Operations API")
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserRepository userRepository;

    @Inject
    CommentRepository commentRepository;

    @Operation(summary = "Get user information by ID")
    @GET
    @Path("/{id}")
    public R<User> getUserById(@PathParam("id") Long id) {
        if (ObjUtil.isEmpty(id)) {
            return R.ok();
        }
        Optional<User> user = userRepository.findById(id.intValue());
        if (user.isEmpty()) {
            return R.error("ID does not exist");
        }
        return R.ok(user.get());
    }

    @Operation(summary = "Get all user information")
    @GET
    @Path("/all")
    public R<List<User>> getUserAll() {
        return R.ok(userRepository.findAll());
    }

    @Operation(summary = "Create a new user")
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/")
    public R createUser(User user) {
        if (ObjUtil.isEmpty(user.getName())) {
            return R.error("Please provide a username");
        }
        if (user.getName().length() > 50) {
            return R.error("Username cannot exceed 50 characters");
        }
        if (!Tool.isValidEmail(user.getEmail())) {
            return R.error("Invalid email format");
        }
        if (!Tool.isValidPhone(user.getPhone())) {
            return R.error("Invalid phone number format");
        }
        User existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail != null) {
            return R.error("Email is already associated with an account");
        }
        User existingUserByPhone = userRepository.findByPhone(user.getPhone());
        if (existingUserByPhone != null) {
            return R.error("Phone number is already associated with an account");
        }
        user.setId(0);
        userRepository.save(user);
        return R.ok();
    }

    @Operation(summary = "Delete a user by ID")
    @DELETE
    @Path("/{id}")
    public R deleteUser(@PathParam("id") Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return R.error("ID does not exist");
        }
        commentRepository.deleteAllByUId(id);
        userRepository.deleteById(id);
        return R.ok();
    }

}
