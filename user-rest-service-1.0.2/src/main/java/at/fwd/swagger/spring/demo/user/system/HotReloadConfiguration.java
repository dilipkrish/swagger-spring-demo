package at.fwd.swagger.spring.demo.user.system;

import org.apache.log4j.Logger;

import io.github.jhipster.loaded.JHipsterReloaderAutoConfiguration;
import io.github.jhipster.loaded.reloader.SpringReloader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * JHipster Hot reload of Spring-Beans demo
 * 
 * see also: http://tux2323.blogspot.co.at/2014/06/hot-reload-support-with-spring-loaded.html
 * 
 * @author johannes.fiala@fwd.at
 *
 */
@Configuration
@Conditional(HotReloadConfiguration.HotReloadCondition.class)
public class HotReloadConfiguration {
	/**
	 * Logger for this class
	 */
	private static final Logger log = Logger.getLogger(HotReloadConfiguration.class);

	public static class HotReloadCondition implements Condition {
		/**
		 * Logger for this class
		 */
		private static final Logger log = Logger.getLogger(HotReloadCondition.class);

		@Override
		public boolean matches(ConditionContext context,
				AnnotatedTypeMetadata metadata) {
			String hotReloadEnabledProperty = context.getEnvironment()
					.getProperty("hotReload.enabled");
			log.info("JHipster hotReload.enabled: " + hotReloadEnabledProperty);
			return "true".equals(hotReloadEnabledProperty);
		}
	}

	@Bean
	public JHipsterReloaderAutoConfiguration reloaderAutoConfiguration() {
		log.info("activating reloadinger configuration");
		// set Package path to monitor
		System.setProperty("hotReload.package.project", "at.fwd.swagger.spring.demo.user");
		
		JHipsterReloaderAutoConfiguration config = new JHipsterReloaderAutoConfiguration();
		return config;
	}

	@Bean
	public SpringReloader springReloader() {
		return new SpringReloader();
	}
}