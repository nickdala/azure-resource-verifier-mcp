package dev.nickdala.mcp.azureresourceverifier.azure;

import com.azure.core.credential.TokenCredential;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.profile.AzureProfile;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.resourcemanager.AzureResourceManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AzureConfig {

    @Bean
    public AzureProfile azureProfile() {
        return new AzureProfile(AzureEnvironment.AZURE);
    }

    @Bean
    public TokenCredential tokenCredential(AzureProfile profile) {
        return new DefaultAzureCredentialBuilder()
                .authorityHost(profile.getEnvironment().getActiveDirectoryEndpoint())
                .build();
    }

    @Bean
    public AzureResourceManager azureResourceManager(AzureProfile profile, TokenCredential tokenCredential) {
        return AzureResourceManager.configure()
                .authenticate(tokenCredential, profile)
                .withDefaultSubscription();
    }
}
