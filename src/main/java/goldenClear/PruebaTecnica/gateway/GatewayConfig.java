package goldenClear.PruebaTecnica.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	@Bean
	public RouteLocator customRouteLocator (RouteLocatorBuilder builder) {
		return builder.routes().route("productos_route",r -> r.path("/api/productos/**").uri("http://localhost:8080")).build();
	}
}
