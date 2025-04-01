package dev.nickdala.mcp.azureresourceverifier.azure;

import com.azure.resourcemanager.AzureResourceManager;
//import com.azure.resourcemanager.resources.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationFinder {
    private static final Logger log = LoggerFactory.getLogger(LocationFinder.class);
    private final AzureResourceManager resourceManager;

    public LocationFinder(AzureResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public List<Location> findAllLocations(String subscriptionId) {
        log.info("Finding all locations for subscription: {}", subscriptionId);

        // Get the list of locations from the resource manager
        return resourceManager.subscriptions().getById(subscriptionId).listLocations().stream()
                .map(location -> new Location(location.name(), location.displayName()))
                .collect(Collectors.toList());

        /*return resourceManager.getCurrentSubscription().listLocations().stream()
                .map(location -> new Location(location.name(), location.displayName()))
                .collect(Collectors.toList());*/
    }

}