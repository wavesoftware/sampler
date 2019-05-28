# Sampler for Java

[![Build Status](https://travis-ci.org/wavesoftware/sampler.svg?branch=develop)](https://travis-ci.org/wavesoftware/sampler) [![Build status](https://ci.appveyor.com/api/projects/status/g0fxplnjxk9kt2s2/branch/develop?svg=true)](https://ci.appveyor.com/project/cardil/sampler/branch/develop) [![Quality Gate](https://sonar.wavesoftware.pl/api/badges/gate?key=pl.wavesoftware.sampler:sampler-parent)](https://sonar.wavesoftware.pl/dashboard/index/pl.wavesoftware.sampler:sampler-parent) [![Technical Dept](https://sonar.wavesoftware.pl/api/badges/measure?key=pl.wavesoftware.sampler:sampler-parent&metric=sqale_debt_ratio)](https://sonar.wavesoftware.pl/dashboard/index/pl.wavesoftware.sampler:sampler-parent) [![codecov](https://codecov.io/gh/wavesoftware/sampler/branch/develop/graph/badge.svg)](https://codecov.io/gh/wavesoftware/sampler) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/pl.wavesoftware.sampler/sampler-spring/badge.svg)](https://maven-badges.herokuapp.com/maven-central/pl.wavesoftware.sampler/sampler-spring)

A typesafe engine for your project examples

## Installation

```xml
<!-- For Spring integration -->
<dependency>
  <groupId>pl.wavesoftware.sampler</groupId>
  <artifactId>sampler-spring</artifactId>
  <version>1.0.0</version>
  <scope>test</scope>
</dependency>
```

## Usage

Simply adding a dependency should do the trick. There will be:

 * logging configured with log4j2, slf4j, jul and jcl
 * proper logging integration with Spring/Spring-Boot, if present, and `EnableAutoConfiguration` 
 is used.
 * color logs
 * logging collector for log4j2 for testing purposes

## Contributing

Contributions are welcome!

To contribute, follow the standard [git flow](http://danielkummer.github.io/git-flow-cheatsheet/) of:

1. Fork it
1. Create your feature branch (`git checkout -b feature/my-new-feature`)
1. Commit your changes (`git commit -am 'Add some feature'`)
1. Push to the branch (`git push origin feature/my-new-feature`)
1. Create new Pull Request

Even if you can't contribute code, if you have an idea for an improvement 
please open an [issue](https://github.com/wavesoftware/sampler/issues).

## Requirements

* Java 8+ (Tested on JDK 8, 9, and 11)
* Spring module requires any modern Spring, tested against latest 5.1.x, but should work in Spring 3+ 

## Releases

* `1.0.0` - codename: *BananaBow*
	* First publicly available release
