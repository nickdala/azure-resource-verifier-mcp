package dev.nickdala.mcp.azureresourceverifier.azure;

public enum AppServiceOsEnum {

    WINDOWS("windows"),
    LINUX("linux");

    AppServiceOsEnum(String os) {
    }

    public static AppServiceOsEnum fromString(String osName) {
        for (AppServiceOsEnum os : AppServiceOsEnum.values()) {
            if (os.name().equalsIgnoreCase(osName)) {
                return os;
            }
        }
        throw new IllegalArgumentException("No enum constant " + AppServiceOsEnum.class.getCanonicalName() + "." + osName);
    }
}
