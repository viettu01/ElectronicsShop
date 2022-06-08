package com.tuplv.api.web;

import com.google.gson.Gson;
import com.tuplv.dto.OrderDTO;
import com.tuplv.service.IEmailService;
import com.tuplv.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/order")
public class OrderAPI {
    @Autowired
    IOrderService orderService;

    @Autowired
    IEmailService emailService;

    @PostMapping(value = "/insert", produces = "text/plain;charset=UTF-8")
    public String insertUseProc(@RequestParam("userId") long userId, @RequestParam("fullName") String fullName,
                                @RequestParam("address") String address, @RequestParam("phone") String phone,
                                @RequestParam(value = "note", required = false) String note,
                                @RequestParam("priceTotal") double priceTotal) {
        return new Gson().toJson(orderService.insertOrderAndOrderDetailUseProc(userId, fullName, address, phone, note, priceTotal));
    }

    @GetMapping(value = "/find-by-user-id", produces = "text/plain;charset=UTF-8")
    public String findById(@RequestParam("userId") long userId) {
        return new Gson().toJson(orderService.findByUserId(userId));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateStatus(@RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "status") Integer status) {
        OrderDTO orderDTO = orderService.findById(id);
        orderDTO.setStatus(status);

        OrderDTO order = orderService.save(orderDTO);
        if (order != null) {
            return "success";
        } else {
            return "error";
        }
    }
}
