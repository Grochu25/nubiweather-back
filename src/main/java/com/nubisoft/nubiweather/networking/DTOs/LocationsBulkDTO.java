package com.nubisoft.nubiweather.networking.DTOs;

import java.util.ArrayList;
import java.util.List;

public class LocationsBulkDTO
{
    public List<LocationName> locations = new ArrayList<LocationName>();

    public LocationsBulkDTO(String[] cities)
    {
        for(String city : cities)
            locations.add(new LocationName(city));
    }

    public record LocationName(
            String q
    ){}
}
