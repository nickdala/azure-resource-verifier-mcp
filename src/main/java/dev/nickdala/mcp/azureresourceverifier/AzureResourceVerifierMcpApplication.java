package dev.nickdala.mcp.azureresourceverifier;

import dev.nickdala.mcp.azureresourceverifier.tools.AppServiceMcpTool;
import dev.nickdala.mcp.azureresourceverifier.tools.LocationMcpTool;
import dev.nickdala.mcp.azureresourceverifier.tools.PostgresMcpTool;
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
	public ToolCallbackProvider toolCallbackProvider(LocationMcpTool locationMcpTool,
													 PostgresMcpTool postgresMcpTool,
													 AppServiceMcpTool appServiceMcpTool) {
		return MethodToolCallbackProvider.builder()
				.toolObjects(locationMcpTool, postgresMcpTool, appServiceMcpTool)
				.build();
	}

}
