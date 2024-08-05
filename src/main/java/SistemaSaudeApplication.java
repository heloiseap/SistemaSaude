import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaSaudeApplication {

	private static Logger logger = LogManager.getLogger(SistemaSaudeApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SistemaSaudeApplication.class, args);
		logger.debug("Debug msg");
		logger.info("Info msg");
		logger.error("Error msg");
	}


}
