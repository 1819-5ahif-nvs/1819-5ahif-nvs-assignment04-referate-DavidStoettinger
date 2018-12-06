# `MicroService`

## Beispiel 1

Es wird ein MicroService simmuliert mit den Entitäten Product und Customer, welche über Order kombiniert abgerufen werden können

### Gleichheiten
#### pom.xml

MicroProfile
```
<dependency>
  <groupId>org.eclipse.microprofile</groupId>
  <artifactId>microprofile</artifactId>
  <version>2.1</version>
  <type>pom</type>
  <scope>provided</scope>
</dependency>
```

Die Version von Thorntail
```
<dependency>
    <groupId>io.thorntail</groupId>
    <artifactId>microprofile</artifactId>
    <version>2.2.0.Final</version>
</dependency>
```

Konfiguriert den Thorntail.
Swarm.Portoffset sollte bei jeden server anders sein
```
<build>
    <plugins>
        <plugin>
            <groupId>io.thorntail</groupId>
            <artifactId>thorntail-maven-plugin</artifactId>
            <version>${version.thorntail}</version>
            <executions>
                <execution>
                    <goals>
                        <goal>package</goal>
                    </goals>
                    <configuration>
                        <properties>
                            <swarm.port.offset>10</swarm.port.offset>
                        </properties>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

#### RestConfig
```
@ApplicationScoped
@ApplicationPath("/rs")
public class RestConfig extends Application{
}
```



- Kurze Beschreibung
- Status der Ausarbeitung: funktiniert, funktioniert nicht, weil, ...
- Wie kann das Beispiel gestartet werden? `mvn package`, gefolgt von ... 
- URIs des REST-Endpoints

## Ausarbeitung des Referats
