package com.nkosi.buildingmanteinancefront.repository.rest;

import com.nkosi.buildingmanteinancefront.model.BuildingManager;
import com.nkosi.buildingmanteinancefront.repository.RestAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hashcode on 2015/09/01.
 */
public class RestBuildingManagerAPI implements RestAPI<BuildingManager,Long>{
    final String BASE_URL="http://buildingmanteinance-nkosi.rhcloud.com/api/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public BuildingManager get(Long id) {
        final String url = BASE_URL+"managerids/"+id.toString();
        HttpEntity<BuildingManager> requestEntity = new HttpEntity<BuildingManager>(requestHeaders);
        ResponseEntity<BuildingManager> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, BuildingManager.class);
        BuildingManager subject = responseEntity.getBody();
        return subject;
    }

    @Override
    public String post(BuildingManager entity) {
        final String url = BASE_URL+"buildingmanager/create";
        HttpEntity<BuildingManager> requestEntity = new HttpEntity<BuildingManager>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(BuildingManager entity) {
        final String url = String.valueOf(BASE_URL+"managers/update/"+entity.getId());
        HttpEntity<BuildingManager> requestEntity = new HttpEntity<BuildingManager>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(BuildingManager  entity) {
        final String url = String.valueOf(BASE_URL+"managers/delete/"+entity.getId());
        HttpEntity<BuildingManager> requestEntity = new HttpEntity<BuildingManager>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<BuildingManager> getAll() {
        List<BuildingManager> managers = new ArrayList<>();
        final String url = BASE_URL+"managers/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<BuildingManager[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, BuildingManager[].class);
        BuildingManager[] results = responseEntity.getBody();

        for (BuildingManager  subject : results) {
            managers.add(subject);
        }
        return managers;
    }

    @Override
    public String home() {
        return null;
    }
}
