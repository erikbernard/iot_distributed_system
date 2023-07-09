package br.com.project.distributed_system.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.project.distributed_system.model.Sensor;

@Service
public class SensorProducerService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    String topicUnstructuredData = "SENSOR-DATA-UNSTRUCTURED";
    String topicStructuredData = "STRUCTURED-AND-ANALYZED-SENSOR-DATA";

    String bootstrapServers="127.0.0.1:9092";
    String groupIdSensor ="group_id_sensor";
    String groupIdAnalys ="group_id_analyst";

    @Autowired
    private KafkaTemplate<String, Sensor> kafkaTemplate;

    public void sensorDataGenerator(int namberOfData) {

        for (int index = 0; index < namberOfData; index++) {

            String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(getDate());
            String sensorName = getRandomSensorName();
            String macAddress = generateRandomMacAddress();
            String location = generateRandomLocation();
            List<Double> readings = generateRandomReadings(10, 10.0, 99.99);
            Double minValue = calculateMinValue(readings);
            Double maxValue = calculateMaxValue(readings);
            Double averageValue = calculateAverageValue(readings);
            Date date = getDate();
            var unstructuredData = String.format(Locale.US, 
            "%s, %s, %f, %f, %f, %s, %s @",
                sensorName, macAddress, minValue, maxValue, averageValue, location, formattedDate);

            var key = UUID.randomUUID().toString();
            // logger.info(String.format("Publishing unstructuredData -> { %s }", unstructuredData));
            logger.info("Publishing unstructuredData -> { %s }", unstructuredData);
            // kafkaTemplate.send(topicUnstructuredData,key,unstructuredData);
            var sensor = new Sensor(sensorName, macAddress, minValue, maxValue, averageValue, location, date);
            kafkaTemplate.send(topicUnstructuredData,key,sensor);
        }
    }

    private Date getDate() {
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();
        calendar.set(Calendar.MONTH, random.nextInt(12));
        System.out.println("Nova data: " + calendar.getTime());

        return calendar.getTime();
    }

    private List<Double> generateRandomReadings(int numReadings, Double minValue, Double maxValue) {
        List<Double> readings = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < numReadings; i++) {
            Double value = minValue + (maxValue - minValue) * rand.nextDouble();
            readings.add(Math.round(value * 100.0) / 100.0);
        }

        return readings;
    }

    private Double calculateMinValue(List<Double> readings) {
        Double minValue = Double.MAX_VALUE;
        for (Double reading : readings) {
            if (reading < minValue) {
                minValue = reading;
            }
        }
        return minValue;
    }

    private Double calculateMaxValue(List<Double> readings) {
        Double maxValue = Double.MIN_VALUE;
        for (Double reading : readings) {
            if (reading > maxValue) {
                maxValue = reading;
            }
        }
        return maxValue;
    }

    private Double calculateAverageValue(List<Double> readings) {
        Double sum = 0.0;
        for (Double reading : readings) {
            sum += reading;
        }
        return sum / readings.size();
    }

    private String generateRandomMacAddress() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            String hexByte = Integer.toHexString(rand.nextInt(256));
            if (hexByte.length() == 1) {
                sb.append("0");
            }
            sb.append(hexByte);
            if (i < 5) {
                sb.append(":");
            }
        }

        return sb.toString().toUpperCase();
    }

    private String generateRandomLocation() {
        String[] locations = { "Room A", "Room B", "Room C", "Lobby", "Office", "Kitchen" };
        Random rand = new Random();
        return locations[rand.nextInt(locations.length)];
    }
    public String getRandomSensorName() {
        List<String> sensorNames = new ArrayList<String>();
        sensorNames.add("Forno");
        sensorNames.add("Caldeira");
        sensorNames.add("Estufa");
        Random random = new Random();
        int randomIndex = random.nextInt(sensorNames.size());
        return sensorNames.get(randomIndex)+" - "+generateRandomLocation();
    }

}