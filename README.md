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

Jsonb jsonb = JsonbBuilder.newBuilder().build();
return jsonb.fromJson(new InputStreamReader(con.getInputStream()),Customer.class);
```

Nachdem MicroProfile Jsonb unterstützt, brauchen wir nur noch eine dependency damit es funktioniert.

#### pom.xml

```
<dependency>
    <groupId>org.eclipse</groupId>
    <artifactId>yasson</artifactId>
    <version>1.0</version>
</dependency>
```

### Starten der Programme

Nachdem wir in jeden Project thorntail verwenden können wir nach den Packaging
```java -jar target/<artifact>-thorntail.jar```
zum starten ausführen.

### Rest-Endpoints

- ```localhost:8080/rs/Order```
- ```localhost:8085/rs/Customer```
- ```localhost:8090/rs/Product```

## Ausarbeitung des Referats

[Presentation](Presentation/Microservices.pdf)
