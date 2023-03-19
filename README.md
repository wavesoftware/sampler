# Sampler for Java

A typesafe engine for your project examples

## Installation

```xml
<!-- For Spring integration -->
<dependency>
  <groupId>pl.wavesoftware.sampler</groupId>
  <artifactId>sampler-spring</artifactId>
  <version>1.1.0</version>
  <scope>test</scope>
</dependency>
```

## Usage


### Basic usage

You can create a sample classes by implementing `Sampler<T>` interface.

```java
@Sample
public final class Jsr305Artifact implements Sampler<Artifact> {
  private final SamplerContext context;

  Jsr305Artifact(SamplerContext context) {
    this.context = context;
  }

  @Override
  public Artifact create() {
    return new MavenlikeArtifact(
      context,
      "jsr305",
      "com.google.code.findbugs",
      new Semver("3.0.2")
    );
  }
}
```

The `SampleContext` can be used to get instances of other samples, to achieve consistency across complex sample graphs.

```java
@Sample
public final class SimpleProject implements Sampler<Project> {

  private final SamplerContext context;

  SimpleProject(SamplerContext context) {
    this.context = context;
  }

  @Override
  public Project create() {
    Path root = context.get(ProjectRoot.class);
    return new AbstractProject(root, "simple") {
      @Override
      public Set<Artifact> dependencies() {
        return HashSet
            .of(HibernateArtifact.class, Jsr305Artifact.class)
            .map(context::get);
      }
    };
  }
}
```

### Vanilla Java

To use Sampler in vanilla Java, you need to create a `SamplerContext` instance,
and register all samples in it.

```java
class SampleTests {
  @Test
  void samples() {
    var samplers = HashMap.of(JohnDoe.class, (Sampler<User>) () -> User.builder()
      .id(43L)
      .name("John Doe")
      .build()
    );
    try (var ctx = new StaticSamplerContext(samplers)) {
      User user = ctx.get(JohnDoe.class);
      assertThat(user).isNotNull();
    }
  }
}
```

### Spring integration

To use Sampler in Spring, you need to autowire a `SamplerContext` instance. If
you are using the Spring Boot, the `SamplerContext` is already available as a
bean. You need to register all samples using `@Sample` annotation.

```java
@Configuration
class Samples {
  @Bean
  Sampler<User> johnDoe() {
    return () -> User.builder()
      .id(43L)
      .name("John Doe")
      .build();
  }
}
```

Using it is also simple

```java
@SpringBootTest
class SampleTests {
  @Autowired
  private SamplerContext ctx;

  @Test
  void samples() {
    User user = ctx.get(JohnDoe.class);
    assertThat(user).isNotNull();
  }
}
```

### Random samples

If you like to randomize your samples, you can use user the
`SamplerContext#controller()` method to get a `SamplerController` instance.
Using sample controller you can fetch a `Random` instance, and use it to create
random samples.

If you happen to encounter a situation where you need to re-create a given
randomized execution, like a failure on CI system, you can use the
`sampler.seed` system property, or `SAMPLER_SEED` environment variable to
recreate same execution. The seed used by `DefaultRandomSource` is printed on
console during the execution.

```java
@Sample
@RequiredArgsConstructor
class JohnDoe implements Sampler<User> {
  private final SamplerContext ctx;

  @Override
  public User create() {
    return User.builder()
      .id(ctx.controller().random().nextLong())
      .name("John Doe")
      .build();
  }
}
```

## Contributing

Contributions are welcome! To contribute, file a PR.

Even if you can't contribute code, if you have an idea for an improvement
please open an [issue](https://github.com/wavesoftware/sampler/issues).

## Requirements

* Java 11+ (Tested on JDK 11, and 17)
* Spring module requires any modern Spring, tested against latest 6.x, but
  should work in Spring 3+ as well.

## Releases

* `1.1.0` - codename: *FriarySparkle*
  * Migrate to Spring Boot 3.x

* `1.0.0` - codename: *BananaBow*
	* First publicly available release
