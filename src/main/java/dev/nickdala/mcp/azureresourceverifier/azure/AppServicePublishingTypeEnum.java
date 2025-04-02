package dev.nickdala.mcp.azureresourceverifier.azure;

public enum AppServicePublishingTypeEnum {
    /*
     * Publish a jar or .NET assembly to web app.
     */
    CODE("code"),

    /*
     * Publish a Docker image to web app.
     */
    CONTAINER("container");

    AppServicePublishingTypeEnum(String container) {
    }

    public static AppServicePublishingTypeEnum fromString(String publishingType) {
        return switch (publishingType.toUpperCase()) {
            case "CODE" -> CODE;
            case "CONTAINER" -> CONTAINER;
            default -> throw new IllegalArgumentException("Invalid publishing type: " + publishingType);
        };
    }
}
