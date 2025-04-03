package com.nubisoft.nubiweather.networking.DTOs;

import java.util.List;

public class ResultWeatherApiBulkDTO<T>
{
    public List<Bulk<T>> bulk;

    public record Bulk<T>(
        T query
    ){
    }
}
