FROM maven:3.6.1-jdk-8
EXPOSE 80

#I read online its good to do it this way :)
ARG SPRING_PROFILES
ENV spring_profiles_active=$SPRING_PROFILES

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package 

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/urandom -jar /usr/src/app/target/*.jar" ]