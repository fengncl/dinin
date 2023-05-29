package com.ttttt.core.controller;

import cn.hutool.core.util.ObjUtil;
import com.ttttt.core.db.dao.CanteenRepository;
import com.ttttt.core.db.dao.CommentRepository;
import com.ttttt.core.db.dao.UserRepository;
import com.ttttt.core.db.entity.Canteen;
import com.ttttt.core.db.entity.Comment;
import com.ttttt.core.db.entity.User;
import com.ttttt.core.util.R;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Tag(name = "评论")
@Path("/comment")
@Produces(MediaType.APPLICATION_JSON)
public class CommentController {
    @Inject
    CommentRepository commentRepository;

    @Inject
    CanteenRepository canteenRepository;

    @Inject
    UserRepository userRepository;


    @Operation(summary = "评论餐厅")
    @POST
    @Path("/")
    //接口返回的数据是json
    @Consumes(MediaType.APPLICATION_JSON)
    public R createComment(Comment comment) {
        if (ObjUtil.isEmpty(comment.getContent())) {
            return R.error("请填写评论");
        }
        if (comment.getContent().length() > 300) {
            return R.error("评论长度不能大于300");
        }
        if (ObjUtil.isEmpty(comment.getGrade())) {
            return R.error("请填写评分");
        }
        if (comment.getGrade() < 0) {
            return R.error("评分不能小于0");
        }
        if (comment.getGrade() > 5) {
            return R.error("评分不能大于5");
        }
        Optional<Canteen> canteen = canteenRepository.findById(comment.getcId());
        if (canteen.isEmpty()) {
            return R.error("餐厅id不存在");
        }
        Optional<User> user = userRepository.findById(comment.getuId());
        if (user.isEmpty()) {
            return R.error("用户id不存在");
        }
        Comment comment2 = commentRepository.findByCIdAndUId(comment.getcId(), comment.getuId());
        if (comment2 != null) {
            return R.error("已经在本餐厅评论过了");
        }
        comment.setId(0);
        commentRepository.save(comment);
        return R.ok("评论成功");
    }

    @Operation(summary = "根据用户的id查询所有评论")
    @GET
    @Path("/all")
    public R<List<Comment>> getComments(Integer uid) {
        return R.ok(commentRepository.findByUId(uid));
    }


}
