package br.com.project.distributed_system.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataAnalyst {
    private static List<Sensor> sensors;

    public static List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        DataAnalyst.sensors = sensors;
    }

    public static void parseData(String sensorData) {
        String[] sensorStrings = sensorData.split("@");

        // Criar uma lista para armazenar os objetos Sensor
        List<Sensor> sensorList = new ArrayList<>();

        // Iterar sobre os objetos Sensor
        for (String sensorString : sensorStrings) {
            // Dividir a string de um único Sensor em partes separadas por vírgula
            String[] values = sensorString.split(",");

            // Remover espaços em branco dos valores
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].trim();
            }

            // Extrair os valores individuais
            String sensorName = values[0];
            String macAddress = values[1];
            Double minValue = Double.parseDouble(values[2]);
            Double maxValue = Double.parseDouble(values[3]);
            Double averageValue = Double.parseDouble(values[4]);
            String location = values[5];

            // Converter a data
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(values[6], formatter);

            // Criar uma instância de Sensor
            // Sensor sensor = new Sensor(sensorName, macAddress, minValue, maxValue, averageValue, location, date);

            // Adicionar o objeto Sensor à lista
            // sensorList.add(sensor);
        }

        // Imprimir os valores dos sensores
        for (Sensor sensor : sensorList) {
            System.out.println("Sensor Name: " + sensor.getSensorName());
            System.out.println("MAC Address: " + sensor.getMacAddress());
            System.out.println("Minimum Value: " + sensor.getMinValue());
            System.out.println("Maximum Value: " + sensor.getMaxValue());
            System.out.println("Average Value: " + sensor.getAverageValue());
            System.out.println("Location: " + sensor.getLocation());
            System.out.println("Date: " + sensor.getDate());
            System.out.println();
        }

        sensors = sensorList;
    }
}
