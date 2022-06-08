package com.tuplv.controller.admin;

import com.tuplv.dto.CategoryDTO;
import com.tuplv.dto.OrderDTO;
import com.tuplv.service.IOrderService;
import com.tuplv.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value = "orderControllerOfAdmin")
@RequestMapping(value = "/admin/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @Autowired
    MessageUtil messageUtil;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                 HttpServletRequest request) {
        OrderDTO model = new OrderDTO();
        model.setPage(page);
        model.setLimit(limit);
        ModelAndView mav = new ModelAndView("admin/order/list");
        Pageable pageable = new PageRequest(page - 1, limit);
        model.setListResult(orderService.findAll(pageable));
        model.setTotalItem(orderService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id,
                                 OrderDTO orderDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/order/edit");
        OrderDTO model = new OrderDTO();
        if (id != null) {
            model = orderService.findById(id);
            if (orderDTO != null && orderDTO.getStatus() != null) {
                model = orderDTO;
            }
        }

        mav.addObject("message", request.getAttribute("message"));
        mav.addObject("alert", request.getAttribute("alert"));
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "status") Integer status,
                             HttpServletRequest request) {
        OrderDTO orderDTO = orderService.findById(id);
        orderDTO.setStatus(status);

        OrderDTO order = orderService.save(orderDTO);
        if (order != null) {
            request.setAttribute("alert", "success");
            request.setAttribute("message", "Cập nhật thành công");

            return editPage(order.getId(), orderDTO, request);
        } else {

            request.setAttribute("message", "Lỗi hệ thống");
            request.setAttribute("alert", "danger");
            return showList(1, 10, request);
        }
    }
}
