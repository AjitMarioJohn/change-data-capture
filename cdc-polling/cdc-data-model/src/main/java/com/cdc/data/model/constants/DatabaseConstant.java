package com.cdc.data.model.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DatabaseConstant {

    public static final String DB_URL = "r2dbc:h2:mem:///data-change;DB_CLOSE_DELAY=-1;";
    public static final String DB_USER = "test";
    public static final String DB_PASSWORD = "test";

}
