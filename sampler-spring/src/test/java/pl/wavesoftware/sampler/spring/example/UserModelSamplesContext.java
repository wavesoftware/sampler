package pl.wavesoftware.sampler.spring.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import pl.wavesoftware.sampler.spring.Sample;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 0.1.0
 */
@ComponentScan(includeFilters = @Filter(Sample.class))
@EnableAutoConfiguration
public class UserModelSamplesContext {
}
