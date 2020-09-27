package com.timothy.webui.service;

import com.timothy.webui.bean.DepartmentBean;
import com.timothy.webui.bean.DepartmentTableJsonResult;
import com.timothy.webui.bean.TableResultRoot;
import com.timothy.webui.config.AjaxResult;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/24 15:43
 */
public interface DepartmentService {
    TableResultRoot<DepartmentTableJsonResult> ListDepartment(Integer typeId);

    public AjaxResult InitDepartmentInfo();

    public void CreateDepartmentBean(DepartmentBean departmentBean);
}
