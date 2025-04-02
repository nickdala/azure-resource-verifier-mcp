package dev.nickdala.mcp.azureresourceverifier.azure;

import com.azure.core.util.Context;
import com.azure.resourcemanager.appservice.AppServiceManager;
import com.azure.resourcemanager.appservice.fluent.ResourceProvidersClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    private final AppServiceManager appServiceManager;

    public AppService(AppServiceManager appServiceManager) {
        this.appServiceManager = appServiceManager;
    }

    public List<AppServiceRegionResult> getRegions(AppServiceOsEnum os, AppServicePublishingTypeEnum publishingType) {
        var resourceProvidersClient = getResourceProvidersClient();
        var linux = os == AppServiceOsEnum.LINUX;
        var xeon = publishingType == AppServicePublishingTypeEnum.CONTAINER && os == AppServiceOsEnum.WINDOWS;
        var regions = resourceProvidersClient.listGeoRegions(null, linux, xeon,false, Context.NONE);
        return regions.stream()
                .map(region -> new AppServiceRegionResult(region.name(), region.displayName()))
                .toList();
    }

    
    /**
     * Gets the resource providers client.
     *
     * @return the ResourceProvidersClient instance
     */
    private ResourceProvidersClient getResourceProvidersClient() {
        return this.appServiceManager.serviceClient().getResourceProviders();
    }


}
