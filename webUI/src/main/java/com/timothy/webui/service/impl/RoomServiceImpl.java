package com.timothy.webui.service.impl;

import com.timothy.webui.bean.RoomBean;
import com.timothy.webui.bean.RoomInfo;
import com.timothy.webui.config.RoomProperties;
import com.timothy.webui.service.RoomService;
import com.timothy.webui.utils.RestTemplateUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/18 15:13
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Resource
    RestTemplateUtils restTemplateUtils;

    @Resource
    RestTemplate restTemplate;

    @Resource
    RoomProperties roomProperties;

    @Override
    public Integer GetTotalRoomNum() {
        return DataProvider(1).getTotal();
    }

    @Override
    public String AdjustMajor(Long[] beds, Long facultyId, Long majorId, Long classId) {
        StringBuilder builder = new StringBuilder();
        if (beds.length == 1) {
            builder.append(beds[0]);
        }
        if (beds.length > 1) {
            for (int i = 0; i < beds.length - 1; i++) {
                builder.append(beds[i]).append(",");
            }
            builder.append(beds[beds.length - 1]);
        }
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("level", String.valueOf(1));
        params.add("beds", builder.toString());
        params.add("facultyId", String.valueOf(facultyId));
        params.add("majorId", String.valueOf(majorId));
        if (roomProperties.getPresortLevel() == 1) {
            params.add("classId", String.valueOf(classId));
        }
        HttpEntity<MultiValueMap<String, String>> request = restTemplateUtils.getRequest(params);
        return restTemplate.postForObject(roomProperties.getAdjustMajor(), request, String.class);
    }

    @Override
    public String AdjustCancel(Long beds) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("beds", String.valueOf(beds));
        HttpEntity<MultiValueMap<String, String>> request = restTemplateUtils.getRequest(params);
        return restTemplate.postForObject(roomProperties.getAdjustCancel(), request, String.class);
    }

    @Override
    public RoomBean DataProvider(Integer page) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("_search", "false");
        params.add("nd", String.valueOf(System.currentTimeMillis()));
        params.add("rows", "20");
        if (page == null) page = 1;
        params.add("page", String.valueOf(page));
        params.add("sidx", "");
        params.add("sord", "asc");
        HttpEntity<MultiValueMap<String, String>> request = restTemplateUtils.getRequest(params);
        return restTemplate.postForObject(roomProperties.getDormAjustDataProvider(), request, RoomBean.class);
    }

    @Override
    public synchronized void initRoomInfo() throws InterruptedException {
        RoomInfo.destroy();
        Integer integer = this.GetTotalRoomNum();
        for (int i = 0; i < integer; i++) {
            Thread.sleep(300);
            RoomBean roomBean = this.DataProvider(i);
            RoomInfo.putRoomBean(roomBean);
        }
        System.out.println(RoomInfo.getRoomMap());
    }
}
