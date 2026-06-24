package com.assesment.bajaj.service;

import com.assesment.bajaj.dto.BFHLRequest;
import com.assesment.bajaj.dto.BFHLResponse;
import org.springframework.stereotype.Service;
 
import java.util.ArrayList;
import java.util.List;


@Service
public class BFHLServiceImpl implements BFHLService{
    
    // Hardcoded user details for this challenge
    private static final String USER_ID = "tej_kaur_02011996";
    private static final String EMAIL = "tej@chitkara.com";
    private static final String ROLL_NUMBER = "CSE123456";
 
    @Override
    public BFHLResponse processData(BFHLRequest request) {
        try {
            List<String> data = request.getData();
            if (data == null || data.isEmpty()) {
                return buildErrorResponse();
            }
 
            List<String> oddNumbers = new ArrayList<>();
            List<String> evenNumbers = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            List<String> specialCharacters = new ArrayList<>();
            long sum = 0;
 
            // Process each element
            for (String element : data) {
                if (element == null || element.isEmpty()) {
                    continue;
                }
 
                // Check if it's a number (can be multi-digit)
                if (isNumber(element)) {
                    long num = Long.parseLong(element);
                    sum += num;
 
                    if (num % 2 == 0) {
                        evenNumbers.add(element);
                    } else {
                        oddNumbers.add(element);
                    }
                } else if (element.length() == 1 && Character.isLetter(element.charAt(0))) {
                    // Single alphabetic character
                    alphabets.add(element.toUpperCase());
                } else if (isAlphabetic(element)) {
                    // Multi-character alphabetic string
                    alphabets.add(element.toUpperCase());
                } else if (element.length() == 1 && !Character.isLetterOrDigit(element.charAt(0))) {
                    // Single special character
                    specialCharacters.add(element);
                } else {
                    // Could be multiple special characters or mixed
                    for (char c : element.toCharArray()) {
                        if (!Character.isLetterOrDigit(c)) {
                            specialCharacters.add(String.valueOf(c));
                        } else if (Character.isLetter(c)) {
                            // Handle letters in mixed strings
                            alphabets.add(String.valueOf(c).toUpperCase());
                        }
                    }
                }
            }
 
            // Generate concat_string: reverse order of alphabets with alternating caps
            String concatString = generateConcatString(alphabets);
 
            return BFHLResponse.builder()
                    .isSuccess(true)
                    .userId(USER_ID)
                    .email(EMAIL)
                    .rollNumber(ROLL_NUMBER)
                    .oddNumbers(oddNumbers)
                    .evenNumbers(evenNumbers)
                    .alphabets(alphabets)
                    .specialCharacters(specialCharacters)
                    .sum(String.valueOf(sum))
                    .concatString(concatString)
                    .build();
 
        } catch (Exception e) {
            return buildErrorResponse();
        }
    }
 
    /**
     * Check if string is a valid number
     */
    private boolean isNumber(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
 
    /**
     * Check if string contains only alphabetic characters
     */
    private boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z]+");
    }
 
    /**
     * Generate concat string: alphabets in reverse with alternating caps
     * Logic: reverse the alphabet list, then apply alternating uppercase/lowercase
     */
    private String generateConcatString(List<String> alphabets) {
        if (alphabets == null || alphabets.isEmpty()) {
            return "";
        }
 
        // Reverse the alphabets list
        List<String> reversed = new ArrayList<>(alphabets);
        java.util.Collections.reverse(reversed);
 
        StringBuilder result = new StringBuilder();
        boolean isUpperCase = true;
 
        for (String letter : reversed) {
            if (isUpperCase) {
                result.append(letter.toUpperCase());
            } else {
                result.append(letter.toLowerCase());
            }
            isUpperCase = !isUpperCase;
        }
 
        return result.toString();
    }
 
    /**
     * Build error response
     */
    private BFHLResponse buildErrorResponse() {
        return BFHLResponse.builder()
                .isSuccess(false)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(new ArrayList<>())
                .evenNumbers(new ArrayList<>())
                .alphabets(new ArrayList<>())
                .specialCharacters(new ArrayList<>())
                .sum("0")
                .concatString("")
                .build();
    }
}