package pl.wavesoftware.sampler.example.sample.user;

import org.springframework.context.annotation.Bean;
import pl.wavesoftware.sampler.api.SamplerContext;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 0.1.0
 */
public class UserSamplerContext {
  @Bean
  JohnDoe johnDoe(SamplerContext samplerContext) {
    return new JohnDoeSampler(samplerContext);
  }
}
