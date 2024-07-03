plugins {
    id("java")
}

group = "dev.kotler"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    version = 17
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("com.codeborne:selenide:7.2.1")
    testImplementation("org.slf4j:slf4j-nop:2.0.13" )
}

tasks.compileTestJava {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}