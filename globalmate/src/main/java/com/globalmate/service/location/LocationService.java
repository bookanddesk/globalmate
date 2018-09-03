package com.globalmate.service.location;

import com.alibaba.fastjson.JSONObject;
import com.globalmate.data.dao.mapper.LocationCnEnMapper;
import com.globalmate.data.dao.mapper.LocationEnMapper;
import com.globalmate.data.dao.mapper.LocationMapper;
import com.globalmate.data.entity.Location;
import com.globalmate.data.entity.LocationCnEn;
import com.globalmate.data.entity.LocationEn;
import com.globalmate.service.excel.AbstractExcelService;
import com.globalmate.service.excel.ExcelInfo;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author XingJiajun
 * @Date 2018/8/7 15:27
 * @Description
 */
@Service
public class LocationService extends AbstractExcelService implements ILocationService {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private LocationEnMapper locationEnMapper;
    @Autowired
    private LocationCnEnMapper locationCnEnMapper;

    @Override
    public List<String> getCountries(boolean isEN) {
        return isEN ? locationEnMapper.distinctCountries() : locationMapper.distinctCountries();
    }

    @Override
    public Object getLocations(boolean isEN, Location location) {
        location = Optional.ofNullable(location).orElse(new Location());
        return isEN ? locationEnMapper.queryLike(location) : locationMapper.queryLike(location);
    }

    @Override
    public boolean countryEquals(String country, String target) {
        if (StringUtils.isBlank(country) || StringUtils.isBlank(target)) {
            return false;
        }

        if (StringUtils.equalsIgnoreCase(country, target)) {
            return true;
        }

        LocationCnEn locationCnEn = locationCnEnMapper.selectByCountry(target);

        if (locationCnEn == null) {
            return false;
        }

        if (StringUtils.equalsIgnoreCase(country, locationCnEn.getCountryEn())) {
            return true;
        }

        if (StringUtils.equalsIgnoreCase(country, locationCnEn.getCountryCn())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean cityEquals(String city, String target) {
        if (StringUtils.isBlank(city) || StringUtils.isBlank(target)) {
            return false;
        }

        if (StringUtils.equalsIgnoreCase(city, target)) {
            return true;
        }

        LocationCnEn locationCnEn = locationCnEnMapper.selectByCity(target);

        if (locationCnEn == null) {
            return false;
        }

        if (StringUtils.equalsIgnoreCase(city, locationCnEn.getCityEn())) {
            return true;
        }

        if (StringUtils.equalsIgnoreCase(city, locationCnEn.getCityCn())) {
            return true;
        }

        return false;
    }

    @Override
    public List<Location> getCountriesWithInitials(boolean isEN) {
        return isEN ? locationEnMapper.selectCountries() : locationMapper.selectCountries();
    }

    @Override
    public List<Location> getCitiesWithInitials(boolean isEN, Location location) {
        location = Optional.ofNullable(location).orElse(new Location());
        return isEN ? locationEnMapper.selectCities(location) : locationMapper.selectCities(location);
    }


    public void resolveLocation(String fileName) throws DocumentException {
        if (fileName == null) {
//            fileName = "/LocList.xml";
            fileName = "/EnLocList.xml";
        }
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(fileName);
        treeWalk(document.getRootElement());
    }

    private void treeWalk(Element element) {
//        List<Location> locations = Lists.newArrayList();
        List<LocationEn> locationEns = Lists.newArrayList();
        final String COUNTRYREGION = "CountryRegion",
                STATE = "State",
                CITY = "City",
                NAME = "Name",
                CODE = "Code";
        for (Iterator<Element> itCR = element.elementIterator(COUNTRYREGION); itCR.hasNext();) {
            Element countryRegionEle = itCR.next();
            String countryRegion = countryRegionEle.attributeValue(NAME);
            System.out.println(countryRegion);
            for (Iterator<Element> itSt = countryRegionEle.elementIterator(STATE); itSt.hasNext();) {
                Element stateEle = itSt.next();
                String state = stateEle.attributeValue(NAME);
                System.out.println(countryRegion + " > " + state);
                for (Iterator<Element> itCity = stateEle.elementIterator(CITY); itCity.hasNext();) {
                    Element cityEle = itCity.next();
                    String city = cityEle.attributeValue(NAME);
                    System.out.println(countryRegion + " > " + state+ " > " + city);
//                    Location location = new Location();
//                    location.setId(IdGenerator.generateId());
//                    location.setCountryregion(countryRegion);
//                    location.setState(state);
//                    location.setCity(city);
//                    locations.add(location);

                    LocationEn locationEn = new LocationEn();
                    locationEn.setId(IdGenerator.generateId());
                    locationEn.setCountryregion(countryRegion);
                    locationEn.setState(state);
                    locationEn.setCity(city);
                    locationEns.add(locationEn);
                }
            }
        }
//        addLocations(locations);
        addLocationEns(locationEns);
    }


    public int addLocations(List<Location> locations) {
        if (CollectionUtils.isNotEmpty(locations)) {
            return locationMapper.insertBatch(locations);
        }
        return -1;
    }

    public int addLocationEns(List<LocationEn> locationEns) {
        if (CollectionUtils.isNotEmpty(locationEns)) {
            return locationEnMapper.insertBatch(locationEns);
        }
        return -1;
    }

    public Map<String, String> getFieldCodeNameMap() {
        Map<String, String> map = Maps.newLinkedHashMap();
        map.put("CountryRegion", "国家");
        map.put("Country_Initials", "Country_Initials");
        map.put("State", "省");
//        map.put("state_en","state");
        map.put("City", "城市");
        map.put("City_Initials", "City_Initials");
//        map.put("ext1", "国别");
        return map;
    }


    @Override
    protected String getTableName() {
        return "location_en";
    }
}
