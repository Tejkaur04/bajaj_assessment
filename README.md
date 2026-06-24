# bajaj_assessment
# BFHL REST API - Spring Boot Implementation

A Spring Boot REST API that classifies mixed data types (numbers, alphabets, special characters) and performs various transformations on them.

## Project Overview

This API implements the BFHL challenge specification with the following features:
- **Request Processing**: Accepts an array of mixed data types
- **Data Classification**: Separates even numbers, odd numbers, alphabets, and special characters
- **Calculations**: Computes sum of all numbers and generates a reverse-ordered, alternating-caps concatenation of alphabets
- **Error Handling**: Graceful exception handling with proper HTTP status codes
- **Test Coverage**: Comprehensive JUnit 5 test cases

## Technology Stack

- **Framework**: Spring Boot 3.1.0
- **Language**: Java 17
- **Build Tool**: Maven
- **Testing**: JUnit 5
- **Additional**: Lombok (for reducing boilerplate)

## Project Structure

```
BFHL-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/chitkara/BFHL/
│   │   │       ├── BFHLApiApplication.java
│   │   │       ├── controller/
│   │   │       │   └── BFHLController.java
│   │   │       ├── service/
│   │   │       │   ├── BFHLService.java
│   │   │       │   └── BFHLServiceImpl.java
│   │   │       └── dto/
│   │   │           ├── BFHLRequest.java
│   │   │           └── BFHLResponse.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/chitkara/BFHL/
│               └── service/
│                   └── BFHLServiceTest.java
├── pom.xml
└── README.md
```

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Git

### Local Development Setup

1. **Clone the repository** (or create the project structure as shown above)
   ```bash
   git clone <repository-url>
   cd BFHL-api
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   - The application will start on `http://localhost:8080`
   - API endpoint: `POST http://localhost:8080/BFHL`

5. **Run tests**
   ```bash
   mvn test
   ```

## API Documentation

### Endpoint: POST /BFHL

#### Request
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

#### Response (Success - HTTP 200)
```json
{
  "is_success": true,
  "user_id": "tej_kaur_02011996",
  "email": "tej@chitkara.com",
  "roll_number": "CSE123456",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

#### Response Fields

| Field | Type | Description |
|-------|------|-------------|
| `is_success` | Boolean | Operation status (true/false) |
| `user_id` | String | Format: firstname_lastname_ddmmyyyy (lowercase) |
| `email` | String | User email address |
| `roll_number` | String | College roll number |
| `odd_numbers` | Array | Array of odd numbers as strings |
| `even_numbers` | Array | Array of even numbers as strings |
| `alphabets` | Array | Array of alphabets in uppercase |
| `special_characters` | Array | Array of special characters |
| `sum` | String | Sum of all numbers as string |
| `concat_string` | String | Reverse-ordered alphabets with alternating caps |

#### Status Codes
- **200 OK**: Successful request
- **400 Bad Request**: Invalid request format
- **500 Internal Server Error**: Server error

## Data Processing Logic

### Even/Odd Numbers
- Multi-digit numbers are supported (e.g., "334", "92")
- Numbers are separated into odd and even arrays
- All numbers are returned as strings

### Alphabets
- Single characters and multi-character strings are supported
- All alphabets are converted to uppercase
- Duplicates are preserved

### Special Characters
- Any non-alphanumeric character is classified as special

### Sum Calculation
- Sum of all numeric values
- Result returned as string
- Example: "1" + "334" + "4" = 339 → "339"

### Concatenation String
1. Collect all alphabets in order
2. Reverse the collection
3. Apply alternating uppercase/lowercase pattern
4. Example: [A, R] → [R, A] → "Ra" (R=upper, a=lower)

## Testing

### Run All Tests
```bash
mvn test
```

### Test Coverage
The test suite includes 10 test cases covering:
1. Example A from specification
2. Example B from specification
3. Example C from specification
4. Empty array handling
5. Null data handling
6. Only numbers
7. Only alphabets
8. Only special characters
9. Large numbers
10. Mixed data types

## Deployment Instructions

### Deploy to Railway

1. **Create Railway Account**
   - Visit https://railway.app
   - Sign up with GitHub or other provider

2. **Connect Repository**
   - Create a new project in Railway
   - Connect your GitHub repository

3. **Configure Environment**
   - Railway auto-detects Java/Maven projects
   - Add environment variable if needed:
     ```
     JAVA_VERSION=17
     ```

4. **Deploy**
   - Push code to main branch
   - Railway automatically builds and deploys
   - Your API will be available at: `https://<project-name>.up.railway.app/BFHL`

### Deploy to Render

1. **Create Render Account**
   - Visit https://render.com
   - Sign up

2. **Create New Web Service**
   - Click "New" → "Web Service"
   - Connect GitHub repository

3. **Configure**
   - **Name**: BFHL-api
   - **Environment**: Java
   - **Build Command**: `mvn clean install`
   - **Start Command**: `java -jar target/BFHL-api-1.0.0.jar`
   - **Plan**: Free (or paid as needed)

4. **Deploy**
   - Render will auto-deploy on push
   - Your API will be available at: `https://BFHL-api-<random>.onrender.com/BFHL`

### Deploy to Heroku

1. **Create Heroku Account**
   - Visit https://heroku.com
   - Sign up

2. **Install Heroku CLI**
   ```bash
   # macOS
   brew tap heroku/brew && brew install heroku
   
   # Windows/Linux: Download from https://devcenter.heroku.com/articles/heroku-cli
   ```

3. **Login and Create App**
   ```bash
   heroku login
   heroku create BFHL-api
   ```

4. **Create Procfile** (in project root)
   ```
   web: java -jar target/BFHL-api-1.0.0.jar
   ```

5. **Deploy**
   ```bash
   git push heroku main
   ```

6. **View Logs**
   ```bash
   heroku logs --tail
   ```

## Testing the Deployed API

### Using cURL
```bash
curl -X POST https://your-deployment-url/BFHL \
  -H "Content-Type: application/json" \
  -d '{"data":["a","1","334","4","R","$"]}'
```

### Using Postman
1. Create POST request to your deployment URL + `/BFHL`
2. Set header: `Content-Type: application/json`
3. Body (raw JSON):
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

### Using Python
```python
import requests

url = "https://your-deployment-url/BFHL"
data = {"data": ["a", "1", "334", "4", "R", "$"]}

response = requests.post(url, json=data)
print(response.json())
```

## Customization

### Change User Details
Edit `BFHLServiceImpl.java`:
```java
private static final String USER_ID = "your_name_ddmmyyyy";
private static final String EMAIL = "your_email@example.com";
private static final String ROLL_NUMBER = "YOUR_ROLL_NUMBER";
```

### Change Server Port
Edit `application.properties`:
```properties
server.port=9090
```

## Best Practices Implemented

1. ✅ **Separation of Concerns**: Service layer abstraction with interface
2. ✅ **DTO Pattern**: Request/Response DTOs for API contracts
3. ✅ **Exception Handling**: Graceful error handling and HTTP status codes
4. ✅ **Testing**: Comprehensive JUnit 5 test suite
5. ✅ **Code Quality**: Using Lombok to reduce boilerplate
6. ✅ **Spring Boot Standards**: Following Spring Boot conventions
7. ✅ **JSON Serialization**: Proper JSON field naming with annotations

## Troubleshooting

### Port Already in Use
```bash
# Kill process on port 8080
lsof -ti:8080 | xargs kill -9
```

### Build Failures
```bash
# Clean build
mvn clean install -DskipTests

# Run with verbose output
mvn clean install -X
```

### Test Failures
- Ensure Java 17+ is installed
- Check that all dependencies download correctly
- Run individual test: `mvn test -Dtest=BFHLServiceTest#testProcessDataExampleA`

## Performance Considerations

- Simple array processing with O(n) complexity
- No database or external API calls
- Suitable for production use with standard load balancing

## Security Considerations

- Input validation on request data
- No sensitive information in logs
- Error responses don't expose stack traces
- Ready for HTTPS on deployment platforms

## Future Enhancements

- Add input size validation
- Implement caching for frequent requests
- Add request rate limiting
- Implement comprehensive logging
- Add API documentation with Swagger/OpenAPI

## Support

For issues or questions:
1. Check the test cases for usage examples
2. Review the API documentation section above
3. Check deployment platform logs
4. Verify Java version is 17+

## License

This project is submitted as part of Chitkara University's API Challenge.

---

**Deployment Checklist**
- [ ] Update user details (name, email, roll number)
- [ ] Run all tests locally
- [ ] Build JAR successfully
- [ ] Test API locally before deployment
- [ ] Choose hosting platform (Railway/Render/Heroku)
- [ ] Deploy and verify API is accessible
- [ ] Test deployed API with sample data
- [ ] Submit API endpoint URL to form