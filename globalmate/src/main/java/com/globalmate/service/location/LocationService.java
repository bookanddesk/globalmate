package com.globalmate.service.location;

import com.globalmate.data.dao.mapper.LocationEnMapper;
import com.globalmate.data.dao.mapper.LocationMapper;
import com.globalmate.data.entity.Location;
import com.globalmate.data.entity.LocationEn;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.IdGenerator;
import com.google.common.collect.Lists;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author XingJiajun
 * @Date 2018/8/7 15:27
 * @Description
 */
@Service
public class LocationService implements ILocationService {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private LocationEnMapper locationEnMapper;

    @Override
    public List<String> getCountries(boolean isEN) {
        return isEN ? locationEnMapper.distinctCountries() : locationMapper.distinctCountries();
    }

    @Override
    public Object getLocations(boolean isEN, Location location) {
        location = Optional.ofNullable(location).orElse(new Location());
        return isEN ? locationEnMapper.queryLike(location) : locationMapper.queryLike(location);
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


}
