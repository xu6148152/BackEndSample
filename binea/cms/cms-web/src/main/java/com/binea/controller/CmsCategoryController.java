package com.binea.controller;

import com.binea.cms.dao.model.CmsCategory;
import com.binea.cms.dao.model.CmsCategoryExample;
import com.binea.cms.service.CmsCategoryService;
import com.binea.common.util.Paginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by binea
 * Date: 18/12/2017
 * TIME: 10:11 PM
 */

@RestController
@RequestMapping("/category")
public class CmsCategoryController extends BaseController {

    private final static Logger _log = LoggerFactory.getLogger(CmsCategoryController.class);

    @Autowired
    private CmsCategoryService cmsCategoryService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "redirect:/category/list";
    }

    /**
     * 列表
     *
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "20", value = "rows") int rows,
            HttpServletRequest request, Model model) {

        // 数据列表
        CmsCategoryExample cmsCategoryExample = new CmsCategoryExample();
        cmsCategoryExample.setOffset((page - 1) * rows);
        cmsCategoryExample.setLimit(rows);
        cmsCategoryExample.setOrderByClause("categoryId desc");
        List<CmsCategory> categorys = cmsCategoryService.getMapper().selectByExample(cmsCategoryExample);

        // 分页对象
        long total = cmsCategoryService.getMapper().countByExample(cmsCategoryExample);
        Paginator paginator = new Paginator(total, page, rows, request);

        model.addAttribute("categorys", categorys);
        model.addAttribute("paginator", paginator);
        return "/category/list";
    }

    /**
     * 新增get
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/category/add";
    }

    /**
     * 新增post
     *
     * @param cmsCategory
     * @param binding
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid CmsCategory cmsCategory, BindingResult binding) {
        if (binding.hasErrors()) {
            for (ObjectError error : binding.getAllErrors()) {
                _log.error(error.getDefaultMessage());
            }
            return "/category/add";
        }
        cmsCategory.setCtime(System.currentTimeMillis());
        cmsCategoryService.getMapper().insertSelective(cmsCategory);
        _log.info("新增记录id为：{}", cmsCategory.getCategoryId());
        return "redirect:/category/list";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        cmsCategoryService.getMapper().deleteByPrimaryKey(id);
        return "redirect:/category/list";
    }

    /**
     * 修改get
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("category", cmsCategoryService.getMapper().selectByPrimaryKey(id));
        return "/category/update";
    }

    /**
     * 修改post
     *
     * @param id
     * @param cmsCategory
     * @param binding
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @Valid CmsCategory cmsCategory, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("errors", binding.getAllErrors());
            return "/category/update/" + id;
        }
        cmsCategoryService.getMapper().updateByPrimaryKeySelective(cmsCategory);
        return "redirect:/category/list";
    }

}
