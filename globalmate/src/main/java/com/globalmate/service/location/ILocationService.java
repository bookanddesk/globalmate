package com.globalmate.service.location;

import com.globalmate.data.entity.Location;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/8/7 15:27
 * @Description
 */
public interface ILocationService {

    List<String> getCountries(boolean isEN);

    Object getLocations(boolean isEN, Location location);

    boolean countryEquals(String country, String target);

    boolean cityEquals(String city, String target);

    List<Location> getCountriesWithInitials(boolean isEN);

    List<Location> getCitiesWithInitials(boolean isEN, Location location);

}
