package org.example.utils;


import org.example.collections.City;
import org.example.collections.CityCollection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class FileManager {
    private final CollectionManager collectionManager;

    public FileManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void saveToXml(String filePath){
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Create JAXB context for the CollectionManager class
            JAXBContext context = JAXBContext.newInstance(CityCollection.class);
            Marshaller marshaller = context.createMarshaller();

            // Format the output for better readability
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Marshal the CityCollection object to XML and save it to the file
            marshaller.marshal(collectionManager.getCityCollection(), writer);

            System.out.println("Collection saved to " + filePath);
        } catch (JAXBException e) {
            System.out.println("Error saving collection: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadCollectionFromXml(String filePath) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))){
            // Create a JAXB context for City Collection class
            System.out.println('1');
            JAXBContext context = JAXBContext.newInstance(CityCollection.class);
            System.out.println('2');
            Unmarshaller unmarshaller = context.createUnmarshaller();
            System.out.println(3);

            // Deserialize from XML file
            CityCollection cityCollection = (CityCollection) unmarshaller.unmarshal(inputStream);
            System.out.println('4');

            // Get the TreeSet of Persons
            ArrayDeque<City> loadedCollection = cityCollection.getCities();
            System.out.println('5');
            collectionManager.setCityCollection(loadedCollection);
            System.out.println('6');
            System.out.println("Collection loaded successfully!");
        } catch (JAXBException e) {
            System.out.println("Error loading collection: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }


    public ArrayList<String> loadCommandsFromScript(String filePath){
        ArrayList<String> commands = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String command;
            while ((command = bufferedReader.readLine()) != null) {
                commands.add(command);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return commands;
    }
}
