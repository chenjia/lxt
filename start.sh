java -jar lxt-eureka/target/lxt-eureka-1.0-SNAPSHOT.jar > logs/lxt-eureka-1.0-SNAPSHOT.log &

sleep 15s

java -jar lxt-config/target/lxt-config-1.0-SNAPSHOT.jar > logs/lxt-config-1.0-SNAPSHOT.log &

sleep 15s

java -jar lxt-chat/target/lxt-chat-1.0-SNAPSHOT.jar > logs/lxt-chat-1.0-SNAPSHOT.log &

sleep 25s

java -jar lxt-upload/target/lxt-upload-1.0-SNAPSHOT.jar > logs/lxt-upload-1.0-SNAPSHOT.log &

sleep 25s

java -jar lxt-manage/target/lxt-manage-1.0-SNAPSHOT.jar > logs/lxt-manage-1.0-SNAPSHOT.log &

sleep 25s

java -jar lxt-gateway/target/lxt-gateway-1.0-SNAPSHOT.jar > logs/lxt-gateway-1.0-SNAPSHOT.log &
