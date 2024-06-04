#For Java 17 try this
FROM openjdk:17
#Refer to Maven build -> finalName
ARG JAR_FILE=target/*.jar
#cd /opt/app
WORKDIR /opt/app
# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

#'mvn clean install' ile jar dosyalarını oluşturuyoruz.
#Bu komut docker deamon tarafından bir image oluşturulmasını sağlıyor:
#docker build -t product-cache-service:0.0.1 .
# -t  ---> tagini belirrtiğimiz anlamına geliyor.
# 0.0.1 ---> verdiğimiz versiyon
# En sondaki nokta ise dizini belirtiyoruzç


#docker-compose -p stock-management -f product-cache-service.yml up -d