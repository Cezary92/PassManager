package Model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PasswordManagerModel {
    private Map<String, String> passwordMap;
    private String filename;

    public PasswordManagerModel(String filename){
        this.passwordMap = new HashMap<>();
        this.filename = filename;
        loadPasswordFromFile();
    }
    // Returns the map containing username-password pairs.
    public Map<String, String> getPasswordMap(){
        return passwordMap;
    }
    // Adds a new username-password pair to the map and saves the changes to the file.
    public void addPassword(String username, String password){
        passwordMap.put(username, password);
        savePasswordsToFile();
    }
    // Removes the specified username-password pair from the map and saves the changes to the file.
    public void removePassword(String username){
        passwordMap.remove(username);
        savePasswordsToFile();
    }

    // Saves username-password pairs from the passwordMap to the file.
    private void savePasswordsToFile() {
        try(BufferedWriter writer= new BufferedWriter(new FileWriter(filename))){
            for(Map.Entry<String, String> entry : passwordMap.entrySet()){
                writer.write(entry.getKey()+ " " + entry.getValue());
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Loads username-password pairs from the file into the passwordMap.
    private void loadPasswordFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(" ");
                if(parts.length == 2){
                    passwordMap.put(parts[0], parts[1]);
                }
            }
        }catch (IOException e){
    }
}
}
