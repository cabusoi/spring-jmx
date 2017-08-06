package other;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	@Before("@annotation(other.Logged)")
	public void log(JoinPoint joinpoint) {
		Logger logger = Logger.getLogger(joinpoint.getSourceLocation().getFileName());
		logger.log(Level.INFO, "**** Logging " + joinpoint.getSignature().toShortString() + " Arguments: "
				+ Arrays.toString(joinpoint.getArgs()));
	}
}
