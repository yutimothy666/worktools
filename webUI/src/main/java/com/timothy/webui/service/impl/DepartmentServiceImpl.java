package com.timothy.webui.service.impl;

import com.timothy.webui.bean.DepartmentBean;
import com.timothy.webui.bean.DepartmentInfo;
import com.timothy.webui.bean.DepartmentTableJsonResult;
import com.timothy.webui.bean.TableResultRoot;
import com.timothy.webui.config.AjaxResult;
import com.timothy.webui.service.DepartmentService;
import com.timothy.webui.utils.MyUtils;
import com.timothy.webui.utils.RestTemplateClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/24 15:55
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    RestTemplateClient restTemplateClient;

    public TableResultRoot<DepartmentTableJsonResult> ListDepartment(Integer typeId) {
        ParameterizedTypeReference<TableResultRoot<DepartmentTableJsonResult>> tableResultRootParameterizedTypeReference = new ParameterizedTypeReference<TableResultRoot<DepartmentTableJsonResult>>() {
        };
        String departmentListUrl = "https://yx.tsp189.com/xyyx/department/department!list.shtml?departType={typeId}&";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("_search", "false");
        params.add("nd", String.valueOf(System.currentTimeMillis()));
        params.add("rows", "200");
        params.add("page", "1");
        params.add("sidx", "");
        params.add("sord", "arc");
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("typeId", typeId);

        ResponseEntity<TableResultRoot<DepartmentTableJsonResult>> jsonResponse = restTemplateClient.exchangeDefault(departmentListUrl, params, tableResultRootParameterizedTypeReference, paramMap);
        if (jsonResponse.getStatusCode().is2xxSuccessful()) {
            return jsonResponse.getBody();
        }
        return null;
    }

    @Override
    public AjaxResult InitDepartmentInfo() {
        Map<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            try {
                TableResultRoot<DepartmentTableJsonResult> departmentList = ListDepartment(i);
                data.put(i, departmentList.getRecords());
                DepartmentInfo.setDepartmentInfo(i, departmentList.getGridModel());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return AjaxResult.toAjax("200", "error", null);

            }
        }
        return AjaxResult.success(data);
    }

    //班级用编码
    //专业用名称
    //使用树形结构优化查找
    @Override
    public void CreateDepartmentBean(DepartmentBean departmentBean) {
        if (MyUtils.isNotEmpty(departmentBean.getClassCode())) {
            for (DepartmentTableJsonResult departmentTableJsonResult : DepartmentInfo.getDepartmentInfo(2)) {
                if (departmentTableJsonResult.getCode().equals(departmentBean.getClassCode())) {
                    departmentBean.setClassId(departmentTableJsonResult.getId());
                    departmentBean.setMajorName(departmentTableJsonResult.getParentStr());
                    break;
                }
            }
        }
        for (DepartmentTableJsonResult departmentTableJsonResult : DepartmentInfo.getDepartmentInfo(1)) {
            if (departmentTableJsonResult.getName().equals(departmentBean.getMajorName())) {
                departmentBean.setMajorId(departmentTableJsonResult.getId());
                departmentBean.setFacultyName(departmentTableJsonResult.getParentStr());
                break;
            }
        }
        for (DepartmentTableJsonResult departmentTableJsonResult : DepartmentInfo.getDepartmentInfo(0)) {
            if (departmentTableJsonResult.getName().equals(departmentBean.getFacultyName())) {
                departmentBean.setFacultyId(departmentTableJsonResult.getId());
                break;
            }
        }
    }
}
