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

@Tag(name = "restaurant")
@Path("/canteen")
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantController {
    @Inject
    CanteenRepository canteenRepository;

    @GET
    @Path("/all")
    @Operation(summary = "Check all restaurants")
    public R<List<Canteen>> getCanteenAll() {
        return R.ok(canteenRepository.findAll());
    }

    @Path("/")
    @POST
    @Operation(summary = "Add a restaurant")
    @Consumes(MediaType.APPLICATION_JSON)
    public R createCanteen(Canteen canteen) {
        if (ObjUtil.isEmpty(canteen.getName())) {
            return R.error("Please fill in the restaurant name");
        }
        if (!Tool.isValidPhone(canteen.getPhone())) {
            return R.error("The format of the mobile phone number is incorrect");
        }
        if (canteen.getName().length() > 50) {
            return R.error("The name of the restaurant must be no longer than 50");
        }
        if (!Tool.isValid(canteen.getPostalCode())) {
            return R.error("Incorrect postcode format");
        }
        Canteen canteen1 = canteenRepository.findByPhone(canteen.getPhone());
        if (canteen1 != null) {
            return R.error("The phone number has been bound to the restaurant");
        }
        canteen.setId(0);
        canteenRepository.save(canteen);
        return R.ok("successfully added");
    }


}