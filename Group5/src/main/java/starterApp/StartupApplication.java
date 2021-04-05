package starterApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration

@ComponentScan(basePackages={"com.project.security", "com.project.reservation" , "com.project.database", "com.project.user", "com.project.setup" , "com.project.lookup", "com.project.cancelTrain", "com.project.ticketprint","com.project.findMyTrain"})
@SpringBootApplication
public class StartupApplication {

	public static void main(String args[]) {
		ApplicationContext applicationContext = SpringApplication.run(StartupApplication.class, args);

		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

}
