package pl.wavesoftware.sampler.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import pl.wavesoftware.sampler.example.sample.group.GroupSamplerContext;
import pl.wavesoftware.sampler.example.sample.user.UserSamplerContext;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 0.1.0
 */
@Import({
  UserSamplerContext.class,
  GroupSamplerContext.class
})
@EnableAutoConfiguration
public class UserModelSamplesContext {

}
