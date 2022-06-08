package com.tuplv.controller.admin;

import com.tuplv.converter.CategoryConverter;
import com.tuplv.dto.CategoryDTO;
import com.tuplv.service.ICategoryService;
import com.tuplv.uploadFile.UploadFile;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value = "categoryControllerOfAdmin")
@RequestMapping(value = "/admin/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private UploadFile uploadFile;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                 HttpServletRequest request) {
        CategoryDTO model = new CategoryDTO();
        model.setPage(page);
        model.setLimit(limit);
        ModelAndView mav = new ModelAndView("admin/category/list");
        Pageable pageable = new PageRequest(page - 1, limit);
        model.setListResult(categoryService.findAll(pageable));
        model.setTotalItem(categoryService.getTotalItem());
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
                                 CategoryDTO categoryUpdate,
                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/category/edit");
        CategoryDTO model = new CategoryDTO();
        if (id != null) {
            model = categoryService.findById(id);
            if (categoryUpdate != null && categoryUpdate.getName() != null) {
                model = categoryUpdate;
            }
        }

        mav.addObject("message", request.getAttribute("message"));
        mav.addObject("alert", request.getAttribute("alert"));
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                             HttpServletRequest request) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(id);
        categoryDTO.setName(name);
        categoryDTO.setCode(categoryConverter.toCode(name));

        if (id != null) {
            if (!name.equals(categoryService.findById(id).getName())) {
                if (categoryService.existsByName(name)) {
                    request.setAttribute("alert", "danger");
                    request.setAttribute("message", "Tên danh mục '" + name + "' đã tồn tại");
                    return editPage(id, categoryDTO, request);
                }
            }
        } else {
            if (categoryService.existsByName(name)) {
                request.setAttribute("alert", "danger");
                request.setAttribute("message", "Tên danh mục '" + name + "' đã tồn tại");
                return editPage(id, categoryDTO, request);
            }
        }

        categoryDTO.setAvatar(uploadFile.uploadFile(context, "/template/images/category", avatar));

        CategoryDTO category = categoryService.save(categoryDTO);
        if (category != null) {
            request.setAttribute("alert", "success");
            if (id != null) {
                request.setAttribute("message", "Cập nhật thành công");
            } else {
                request.setAttribute("message", "Thêm mới thành công");
            }
            return editPage(category.getId(), categoryDTO, request);
        } else {
            request.setAttribute("message", "Lỗi hệ thống");
            request.setAttribute("alert", "danger");
            return showList(1, 10, request);
        }
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/category/detail");
        mav.addObject("model", categoryService.findById(id));
        return mav;
    }
}