package Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utility {

    // Generate a random email address
    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
        Random random = new Random();
        String username = generateRandomString(8); // You can adjust the length of the username
        String domain = domains[random.nextInt(domains.length)];
        return username + "@" + domain;
    }

    // Generate a random string of given length
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static int generateRandomNumberBasedOnMaxNumberList(int maxNumber) {
        int randomNumber;
        Random random = new Random();

        // generate a random number based on the last number in the category list
        randomNumber = random.nextInt(maxNumber - 1 + 1) + 1;
        if (randomNumber == maxNumber) {
            randomNumber--;
        }
        return randomNumber;
    }


    public static List<Integer> generateRandomNumbersFromAList(int maxNumber, int numberOfRandomNumbers) {

        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();

        // Use a Set to store unique random numbers
        Set<Integer> uniqueNumbers = new HashSet<>();

        // Generate the specified number of unique random numbers
        for (int i = 0; i < numberOfRandomNumbers; i++) {
            int randomNumber;
            do {
                randomNumber = random.nextInt(maxNumber) + 1; // Generate a random number within the range [1, maxNumber]
            } while (!uniqueNumbers.add(randomNumber)); // Keep generating until a unique number is generated

            randomNumbers.add(randomNumber); // Add the unique random number to the list
        }

        return randomNumbers;
    }

    //read the data file path
    public static String returnDataPath(String fileName) {
        return Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "TestData", fileName).toString();
    }

    // read from json file
    public static String getSingleJsonData(String jsonFilePath, String jsonField) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(jsonFilePath);
        Object obj = jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject.get(jsonField).toString();
    }

    // read from excel sheet
    public static String getExcelData(int RowNum, int ColNum, String SheetName) {
        XSSFWorkbook workBook;
        XSSFSheet sheet;
        String projectPath = System.getProperty("user.dir");
        String cellData = null;
        try {
            workBook = new XSSFWorkbook(projectPath + "/src/test/resources/test_data/data.xlsx");
            sheet = workBook.getSheet(SheetName);
            cellData = sheet.getRow(RowNum).getCell(ColNum).getStringCellValue();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return cellData;
    }

    public static String timeRightNowInRightFormat() {
        // Get the current time in milliseconds since Unix epoch
        long currentTimeMillis = System.currentTimeMillis();

        // Convert milliseconds to Instant
        Instant instant = Instant.ofEpochMilli(currentTimeMillis);

        // Define the format you want
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

        // Convert Instant to a formatted string in UTC (or use a specific time zone)
        String formattedDateTime = formatter.format(instant.atZone(ZoneId.of("UTC")));

        return formattedDateTime;
    }

    public static boolean isSortedAscending(List<String> strings) {
        // Iterate through the list, comparing each string's numeric value with the next one's
        for (int i = 0; i < strings.size() - 1; i++) {
            double currentValue = parseCurrency(strings.get(i));
            double nextValue = parseCurrency(strings.get(i + 1));

            // If current value is greater than next value, the list is not sorted
            if (currentValue > nextValue) {
                return false;
            }
        }
        // If no out-of-order elements were found, the list is sorted
        return true;
    }

    public static double parseCurrency(String currency) {
        // Remove the dollar sign and parse the remaining string as a double
        if (currency != null && currency.startsWith("$")) {
            try {
                return Double.parseDouble(currency.substring(1));
            } catch (NumberFormatException e) {
                // Handle the case where parsing fails
                throw new IllegalArgumentException("Invalid currency format: " + currency, e);
            }
        }
        throw new IllegalArgumentException("Invalid currency format: " + currency);
    }

    public static void clearDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Directory does not exist or is not a directory.");
            return;
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.delete()) {
                    System.out.println("Failed to delete file: " + file.getAbsolutePath());
                }
            }
        }
    }

    // return a string when passing json data
    public static String jsonToString(Map<String, Object> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

}