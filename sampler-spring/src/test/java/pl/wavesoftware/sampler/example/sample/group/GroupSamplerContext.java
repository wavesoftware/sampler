package pl.wavesoftware.sampler.example.sample.group;

import org.springframework.context.annotation.Bean;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 0.1.0
 */
public class GroupSamplerContext {
  @Bean
  Admin admin() {
    return new AdminSampler();
  }

  @Bean
  RemoteDesktop remoteDesktop() {
    return new RemoteDesktopSampler();
  }
}
