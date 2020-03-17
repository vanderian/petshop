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
        public static final String springfox = ":3.0.0-SNAPSHOT";
    }

    public static final class GradlePlugins {
        public static final String springBoot = "org.springframework.boot";
        public static final String springDepManagement = "io.spring.dependency-management";
    }

    public static final class Libs {
        public static final String springBootWeb = "org.springframework.boot:spring-boot-starter-web";
        public static final String springDataJpa = "org.springframework.boot:spring-boot-starter-data-jpa";
        public static final String springBootDev = "org.springframework.boot:spring-boot-devtools";
        public static final String h2 = "com.h2database:h2";
        public static final String lombok = "org.projectlombok:lombok";
        public static final String swagger = "io.springfox:springfox-swagger2" + Versions.springfox;
        public static final String swaggerDataRest = "io.springfox:springfox-data-rest" + Versions.springfox;
        public static final String swaggerUi = "io.springfox:springfox-swagger-ui" + Versions.springfox;
    }

    public static final class TestLibs {
        public static final String springBootTest = "org.springframework.boot:spring-boot-starter-test";
    }
}
