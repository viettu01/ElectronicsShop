package com.tuplv.controller.admin;

import com.tuplv.dto.ProductDTO;
import com.tuplv.service.ICategoryService;
import com.tuplv.service.IProductService;
import com.tuplv.uploadFile.UploadFile;
import com.tuplv.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value = "productControllerOfAdmin")
@RequestMapping(value = "/admin/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    UploadFile uploadFile;

    @Autowired
    MessageUtil messageUtil;

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                 HttpServletRequest request) {
        ProductDTO model = new ProductDTO();
        model.setPage(page);
        model.setLimit(limit);
        ModelAndView mav = new ModelAndView("admin/product/list");
        Pageable pageable = new PageRequest(page - 1, limit);
        model.setListResult(productService.findAll(pageable));
        model.setTotalItem(productService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        mav.addObject("model", model);
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id,
                                 ProductDTO productUpdate,
                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/product/edit");
        ProductDTO model = new ProductDTO();
        if (id != null) {
            model = productService.findById(id);
            if (productUpdate != null && productUpdate.getName() != null) {
                model = productUpdate;
            }
        }

        mav.addObject("message", request.getAttribute("message"));
        mav.addObject("alert", request.getAttribute("alert"));
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "categoryCode") String categoryCode,
                             @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                             @RequestParam(value = "price") double price,
                             @RequestParam("description") String description,
                             HttpServletRequest request) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        productDTO.setName(name);
        productDTO.setCategoryCode(categoryCode);
        productDTO.setPrice(price);
        productDTO.setDescription(description);
        if (id != null) {
            if (!name.equals(productService.findById(id).getName())) {
                if (productService.existsByName(name)) {
                    request.setAttribute("alert", "danger");
                    request.setAttribute("message", "Tên sản phẩm '" + name + "' đã tồn tại");
                    return editPage(id, productDTO, request);
                }
            }
        } else {
            if (productService.existsByName(name)) {
                request.setAttribute("alert", "danger");
                request.setAttribute("message", "Tên sản phẩm '" + name + "' đã tồn tại");
                return editPage(id, productDTO, request);
            }
        }
        productDTO.setAvatar(uploadFile.uploadFile(context, "/template/images/product", avatar));

        ProductDTO product = productService.save(productDTO);
        if (product != null) {
            request.setAttribute("alert", "success");
            if (id != null) {
                request.setAttribute("message", "Cập nhật thành công");
            } else {
                request.setAttribute("message", "Thêm mới thành công");
            }
            return editPage(product.getId(), productDTO, request);
        } else {
            request.setAttribute("message", "Lỗi hệ thống");
            request.setAttribute("alert", "danger");
            return showList(1, 10, request);
        }
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detailPage(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/product/detail");
        mav.addObject("model", productService.findById(id));
        return mav;
    }
}