package dev.nickdala.mcp.azureresourceverifier;

import dev.nickdala.mcp.azureresourceverifier.tools.LocationService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AzureResourceVerifierMcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzureResourceVerifierMcpApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider toolCallbackProvider(LocationService locationService) {
		return MethodToolCallbackProvider.builder()
				.toolObjects(locationService)
				.build();
	}

}
