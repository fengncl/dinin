package com.ttttt.core.controller;

import cn.hutool.core.util.ObjUtil;
import com.ttttt.core.db.dao.CanteenRepository;
import com.ttttt.core.db.entity.Canteen;
import com.ttttt.core.util.R;
import com.ttttt.core.util.Tool;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "餐厅")
@Path("/canteen")
@Produces(MediaType.APPLICATION_JSON)
public class CanteenController {
    @Inject
    CanteenRepository canteenRepository;

    @GET
    @Path("/all")
    @Operation(summary = "查询所有餐厅")
    public R<List<Canteen>> getCanteenAll() {
        return R.ok(canteenRepository.findAll());
    }

    @Path("/")
    @POST
    @Operation(summary = "添加餐厅")
    @Consumes(MediaType.APPLICATION_JSON)
    public R createCanteen(Canteen canteen) {
        if (ObjUtil.isEmpty(canteen.getName())) {
            return R.error("请填写餐厅名");
        }
        if (!Tool.isValidPhone(canteen.getPhone())) {
            return R.error("手机号格式错误");
        }
        if (canteen.getName().length() > 50) {
            return R.error("餐厅名长度不能超过五十");
        }
        if (!Tool.isValid(canteen.getPostalCode())) {
            return R.error("邮编格式错误");
        }
        Canteen canteen1 = canteenRepository.findByPhone(canteen.getPhone());
        if (canteen1 != null) {
            return R.error("手机号已绑定餐厅");
        }
        canteen.setId(0);
        canteenRepository.save(canteen);
        return R.ok("添加成功");
    }


}