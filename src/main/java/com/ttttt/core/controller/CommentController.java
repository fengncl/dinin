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

@Tag(name = "Comments")
@Path("/comment")
@Produces(MediaType.APPLICATION_JSON)
public class CommentController {
    @Inject
    CommentRepository commentRepository;

    @Inject
    CanteenRepository canteenRepository;

    @Inject
    UserRepository userRepository;


    @Operation(summary = "Comment on a canteen")
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public R createComment(Comment comment) {
        if (ObjUtil.isEmpty(comment.getContent())) {
            return R.error("Please fill in the comment");
        }
        if (comment.getContent().length() > 300) {
            return R.error("Comment length cannot exceed 300 characters");
        }
        if (ObjUtil.isEmpty(comment.getGrade())) {
            return R.error("Please provide a rating");
        }
        if (comment.getGrade() < 0) {
            return R.error("Rating cannot be less than 0");
        }
        if (comment.getGrade() > 5) {
            return R.error("Rating cannot be greater than 5");
        }
        Optional<Canteen> canteen = canteenRepository.findById(comment.getcId());
        if (canteen.isEmpty()) {
            return R.error("Canteen ID does not exist");
        }
        Optional<User> user = userRepository.findById(comment.getuId());
        if (user.isEmpty()) {
            return R.error("User ID does not exist");
        }
        Comment existingComment = commentRepository.findByCIdAndUId(comment.getcId(), comment.getuId());
        if (existingComment != null) {
            return R.error("Already commented on this canteen");
        }
        comment.setId(0);
        commentRepository.save(comment);
        return R.ok("Comment added successfully");
    }

    @Operation(summary = "Get all comments by user ID")
    @GET
    @Path("/all")
    public R<List<Comment>> getComments(Integer uid) {
        return R.ok(commentRepository.findByUId(uid));
    }
}
