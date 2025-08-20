package entity.enumirate;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

public class SpecialFeaturesConverterDb implements AttributeConverter<EnumSet<SpecialFeatures>, String> {

    @Override
    public String convertToDatabaseColumn(EnumSet<SpecialFeatures> specialFeatures) {
        if (specialFeatures == null) {return null;}
        return specialFeatures.stream().map(SpecialFeatures::getRealName).collect(Collectors.joining(","));
    }

    @Override
    public EnumSet<SpecialFeatures> convertToEntityAttribute(String s) {
        String[] split = s.split(",");
        if (split.length == 0) {return null;}
        EnumSet<SpecialFeatures> specialFeatures = EnumSet.noneOf(SpecialFeatures.class);
        Arrays.stream(split).map(x -> {
            for (SpecialFeatures specialFeature : SpecialFeatures.values()) {
                if (specialFeature.getRealName().equals(x)) {
                    return specialFeature;
                }
            }
            return null;
        }).forEach(specialFeatures::add);
        return specialFeatures;
    }
}
