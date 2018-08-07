package com.globalmate.service.location;

import com.globalmate.data.entity.Location;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/8/7 15:27
 * @Description
 */
public interface ILocationService {

    List<String> getCountries();

    List<Location> getLocations(Location location);

}
