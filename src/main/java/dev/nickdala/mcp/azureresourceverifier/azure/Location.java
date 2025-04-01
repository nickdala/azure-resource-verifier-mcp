package dev.nickdala.mcp.azureresourceverifier.azure;

public record Location(String name, String displayName) {
    public Location(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}