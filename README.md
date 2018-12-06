# `MicroService`

## Beispiel

Es wird ein MicroService simmuliert mit den Entitäten Product und Customer, welche über Order kombiniert abgerufen werden können

### Gleichheiten der Files
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
Swarm.Port.offset sollte bei jeden server anders sein
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

### Customer und Product

Die Projekte Customer und Product sind fast Ident.

#### Entity

Standard Entity mit einigen Properties.

#### Facade

Wieso eine ConcurrentMap:
Nachdem MicroProfile kein JPA unterstützt werden die Daten in einer Map gespeichert.
Der Unterschied zwischen einer normalen und einer ConcurrentMap ist dass ConcurrentMaps die selben Werte bei jeden Zugriff hat.

Für die Id bei der Map verwenden wir eine variable, welche bei jeden verwenden um 1 erhöht wird
```
private Long latestID = 0L;

private Long getLatestID(){
  latestID++;
  return latestID;
}
```

### Order

Kombiniert Customer und Product

#### Entity

benötigt neben Customer und Product eine 3. Entity Order.

#### Endpoint

Der dazu gehörige Customer und das Product werden via PathParam übergeben.

```
@GET
@Path("{customer}&{product}")
@Produces(MediaType.APPLICATION_JSON)
public Response getOrder(@PathParam("customer") Long customerID, @PathParam("product") Long productID){
    return Response.ok(orderManager.createOrder(customerID,productID)).build();
}
```

#### OrderManager

Setzt requests zum erhalten des Costumers und Products und gibt eine Order zurück
```
URL url = new URL("http://localhost:8085/rs/Customer/"+ID.toString());
HttpURLConnection con = (HttpURLConnection)url.openConnection();
con.setRequestMethod("GET");
Gson gson = new Gson();
JsonParser jp = new JsonParser();
JsonElement el = jp.parse(new InputStreamReader(con.getInputStream()));

return gson.fromJson(el, Customer.class);

```

Grundsätzlich würde MircroProfile Jsonb dafür zur verfügung stellen, jedoch gibt es damit Probleme beim starten

#### pom.xml

Um dieses Problem zu beheben verwende ich Gson
```
<dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.5</version>
        </dependency>
```

### Starten der Programme

Nachdem wir in jeden Project thorntail verwenden

```java -jar target/<artifact>-thorntail.jar```

### Rest-Endpoints

- ```localhost:8080/rs/Order```
- ```localhost:8085/rs/Customer```
- ```localhost:8090/rs/Product```

## Ausarbeitung des Referats

[Presentation](Presentation/Microservices.pdf)
