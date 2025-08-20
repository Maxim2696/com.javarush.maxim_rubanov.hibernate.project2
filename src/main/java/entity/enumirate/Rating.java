package entity.enumirate;

import lombok.Getter;

@Getter
public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC_17");

    private Rating(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    private final String realName;
}
