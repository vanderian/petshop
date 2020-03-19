package sk.vanderian.deps;

// works nice with gradle kotlin script, not very usable in groovy
public final class Dependencies {
    public static final class Modules {
    }

    public static final class Repos {
    }

    public static final class Versions {
        public static final String spring = "2.2.5.RELEASE";
        public static final String springDM = "1.0.9.RELEASE";
        public static final String openApi = ":1.2.33";
        public static final String modelMapper = ":2.3.6";
        public static final String auth0Jwt = ":3.4.0";
    }

    public static final class GradlePlugins {
        public static final String springBoot = "org.springframework.boot";
        public static final String springDepManagement = "io.spring.dependency-management";
    }

    public static final class Libs {
        public static final String springBootWeb = "org.springframework.boot:spring-boot-starter-web";
        public static final String springDataJpa = "org.springframework.boot:spring-boot-starter-data-jpa";
        public static final String springBootDev = "org.springframework.boot:spring-boot-devtools";
        public static final String springBootSecurity = "org.springframework.boot:spring-boot-starter-security";
        public static final String auth0Jwt = "com.auth0:java-jwt" + Versions.auth0Jwt;
        public static final String h2 = "com.h2database:h2";
        public static final String lombok = "org.projectlombok:lombok";
        public static final String openApi = "org.springdoc:springdoc-openapi-ui" + Versions.openApi;
        public static final String openApiRest = "org.springdoc:springdoc-openapi-data-rest" + Versions.openApi;
        public static final String modelMapper = "org.modelmapper:modelmapper" + Versions.modelMapper;
    }

    public static final class TestLibs {
        public static final String springBootTest = "org.springframework.boot:spring-boot-starter-test";
    }
}
