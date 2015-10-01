package com.nkosi.buildingmanteinancefront.repository.rest;

import com.nkosi.buildingmanteinancefront.model.BuildingManager;
import com.nkosi.buildingmanteinancefront.repository.RestAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by NkosikhonaS on 10/1/2015.
 */
public class RestHomePageAPI implements RestAPI<BuildingManager,Long> {

    final String BASE_URL="buildingmanteinance-nkosi.rhcloud.com/api/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();
    @Override
    public BuildingManager get(Long aLong) {
      return null;
    }

    @Override
    public String post(BuildingManager entity) {
        return null;
    }

    @Override
    public String put(BuildingManager entity) {
        return null;
    }

    @Override
    public String delete(BuildingManager entity) {
        return null;
    }

    @Override
    public List<BuildingManager> getAll() {
        return null;
    }

    @Override
    public String home() {
        final String url = BASE_URL+"home/";
        HttpEntity<String> requestEntity = new HttpEntity<String>(requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String welcome = responseEntity.getBody();
        return welcome;
    }
}
