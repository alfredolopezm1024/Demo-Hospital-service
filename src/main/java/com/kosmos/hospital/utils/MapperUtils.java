package com.kosmos.hospital.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperUtils {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <S,D> D map(S source, Class<D> destinationClass){
        return modelMapper.map(source, destinationClass);
    }

    public static <S, D> List<D> mapList(List<S> sourceList, Class<D> destinationClass){
        return modelMapper.map(sourceList, new TypeToken<List<D>>(){}.getType());
    }
}
