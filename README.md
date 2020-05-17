# courier-tracking-system

Courier Tracking System


# SISTEMI CALISTIRMAK
Calistirilacak ortamda Docker yüklü olması gerekmektedir.
Eğer Docker macOS platformunda çalışıyor ise Docker Enginenin en az 4 gb memory limitinin olduğuna emin olunuz.
Proje klasoru icerisinde docker-compose up komutu sistemi ayağa kaldırmaya yetmektedir.
Herhangi bir ekstra konfigürasyona ihtiyac yoktur. 

# API BLUEPRINT
## Courier Geolocation Information [/courier/control]
### Take Courier Streaming Geolocations [POST]

+ time (required, Localdatetime) - Time
+ courier (required, string) - Courier Name(id)
+ lat (required, double) - Latitude
+ lng (required, double) - Longitude


+ Request (application/json;charset=utf-8)

    + Body

            {
                "time":"16/05/2020 03:24:59",
                "courier": "Courier1"
                "lat": 40.986106,
                "lng": 29.1161293
            }

+ Response 202 (application/json;charset=utf-8)

## TotalDistanceTravelled [/courier/totaldistance/{id}]

+ Parameters
    + id: "Courier1" (required, string) - Courier id
 
### Find Total Distance Travelled in Meters [GET]

+ Response 200 (application/json;charset=utf-8)

        {
            "courier": "Courier1",
            "totalDistance":0
        }

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




