package dev.nickdala.mcp.azureresourceverifier.azure;

public record PostgresCapability(String Region, Boolean isRestricted, String RestrictionReason, Boolean isHaEnabled) {
}
