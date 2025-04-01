package dev.nickdala.mcp.azureresourceverifier.azure;

import com.azure.resourcemanager.postgresqlflexibleserver.PostgreSqlManager;
import com.azure.resourcemanager.postgresqlflexibleserver.models.LocationBasedCapabilities;
import com.azure.resourcemanager.postgresqlflexibleserver.models.RestrictedEnum;
import com.azure.resourcemanager.postgresqlflexibleserver.models.ZoneRedundantHaSupportedEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostgreFlexibleServer {

    private final Logger log = LoggerFactory.getLogger(PostgreFlexibleServer.class);

    private final PostgreSqlManager postgreSqlManager;

    public PostgreFlexibleServer(PostgreSqlManager postgreSqlManager) {
        this.postgreSqlManager = postgreSqlManager;
    }

    public List<PostgresCapability> findAll(List<String> locations) {
        log.info("Finding all PostgreSQL flexible servers");
        LocationBasedCapabilities locationBasedCapabilities = postgreSqlManager.locationBasedCapabilities();
        return locations.stream()
                .map(location -> getPostgresCapability(location))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<PostgresCapability> getPostgresCapability(String locationName) {
        log.info("Finding PostgreSQL flexible servers in location: {}", locationName);
        LocationBasedCapabilities locationBasedCapabilities = postgreSqlManager.locationBasedCapabilities();
        var capabilities = locationBasedCapabilities.execute(locationName);
        return capabilities.stream()
                .map(capability -> new PostgresCapability(
                        locationName,
                        capability.restricted() == RestrictedEnum.ENABLED,
                        capability.reason(),
                        capability.zoneRedundantHaSupported() == ZoneRedundantHaSupportedEnum.ENABLED
                ))
                .collect(Collectors.toList());
    }
}
