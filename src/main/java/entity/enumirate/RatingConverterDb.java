package entity.enumirate;

import jakarta.persistence.AttributeConverter;


public class RatingConverterDb implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.getRealName();
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        for (Rating r : Rating.values()) {
            if (r.getRealName().equals(s)) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }
}
