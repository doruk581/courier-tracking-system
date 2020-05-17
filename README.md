# courier-tracking-system

Courier Tracking System

## Derleme

### Build

```
./gradlew clean build
```

### Birim Testleri çalıştırmak

```
./gradlew clean test
```

## Çalıştırma

Projeyi derlemek için

```
./gradlew clean bootJar
```

Derlenen projeyi çalıştırmak için

```
java -jar build/libs/courier-tracking-system-all-0.0.1-SNAPSHOT.jar 
```

Derlenen projeyi development modunda çalıştırmak için

```
java -jar build/libs/courier-tracking-system-all-0.0.1-SNAPSHOT.jar -p 8500 -d
```


