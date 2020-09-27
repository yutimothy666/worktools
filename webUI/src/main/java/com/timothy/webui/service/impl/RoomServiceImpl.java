package com.timothy.webui.service.impl;

import com.timothy.webui.bean.DepartmentBean;
import com.timothy.webui.bean.RoomJSONResult;
import com.timothy.webui.bean.TableResultRoot;
import com.timothy.webui.bean.RoomInfo;
import com.timothy.webui.config.RoomProperties;
import com.timothy.webui.service.DepartmentService;
import com.timothy.webui.service.RoomService;
import com.timothy.webui.utils.RestTemplateClient;
import org.apache.poi.util.StringUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/18 15:13
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Resource
    RestTemplateClient restTemplateClient;
    @Resource
    RoomProperties roomProperties;

    @Override
    public Integer GetTotalRoomNum() {
        return DataProvider(1).getTotal();
    }

    @Override
    public String AdjustMajor(Long[] beds, DepartmentBean departmentBean) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("level", String.valueOf(1));
        params.add("beds", StringUtil.join(beds, ","));
        params.add("facultyId", String.valueOf(departmentBean.getFacultyId()));
        params.add("majorId", String.valueOf(departmentBean.getMajorId()));
        if (departmentBean.getClassId() != null) {
            params.add("classId", String.valueOf(departmentBean.getClassId()));
        }
        return restTemplateClient.postForObjectDefault(roomProperties.getAdjustMajor(), params, String.class);
    }

    @Override
    public String AdjustCancel(Long beds) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("beds", String.valueOf(beds));
        return restTemplateClient.postForObjectDefault(roomProperties.getAdjustCancel(), params, String.class);
    }

    @Override
    public TableResultRoot<RoomJSONResult> DataProvider(Integer page) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("_search", "false");
        params.add("nd", String.valueOf(System.currentTimeMillis()));
        params.add("rows", "8000");
        if (page == null) page = 1;
        params.add("page", String.valueOf(page));
        params.add("sidx", "");
        params.add("sord", "asc");
        ParameterizedTypeReference<TableResultRoot<RoomJSONResult>> typeReference = new ParameterizedTypeReference<TableResultRoot<RoomJSONResult>>() {
        };
        ResponseEntity<TableResultRoot<RoomJSONResult>> rootResponseEntity = restTemplateClient.exchangeDefault(roomProperties.getDormAjustDataProvider(), params, typeReference);
        if (rootResponseEntity.getStatusCode().is2xxSuccessful()) {
            TableResultRoot<RoomJSONResult> body = rootResponseEntity.getBody();
            assert body != null;
            RoomInfo.putRoomBean(body);
            return body;
        }
        return new TableResultRoot<>();
    }

    @Override
    public synchronized Integer initRoomInfo() {
        RoomInfo.destroy();
        TableResultRoot<RoomJSONResult> roomBean = this.DataProvider(0);
        RoomInfo.putRoomBean(roomBean);
        System.out.println(RoomInfo.getRoomMap());
        return roomBean.getRecords();
    }
}
