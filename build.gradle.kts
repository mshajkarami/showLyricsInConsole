
plugins {
    kotlin("jvm") version "2.0.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

}


tasks.test {
    useJUnitPlatform()
}
application {
    mainClass.set("MainKt")
}
kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
application {
    mainClass.set("MainKt")
}

tasks.withType<JavaExec> {
    standardInput = System.`in`
    jvmArgs("-Dfile.encoding=UTF-8")
}

