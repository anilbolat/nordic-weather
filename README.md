# nordic-weather

In this project, we will build a weather API that fetches and returns weather data from a 3rd party API. This project
will help you understand how to work with 3rd party APIs, caching, and environment variables.

# 3rd party weather api
https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/[location]/[date1]/[date2]?key=YOUR_API_KEY
// Dates should be in the format yyyy-MM-dd. For example 2020-10-19 for October 19th, 2020 or 2017-02-03 for February 3rd, 2017.


# remaining tasks
Use environment variables to store the API key and the Redis connection string. This way, you can easily change them without having to modify your code.
Make sure to handle errors properly. If the 3rd party API is down, or if the city code is invalid, make sure to return the appropriate error message.
Implement rate limiting to prevent abuse of your API. You can use a package like express-rate-limit if you are using Node.js or flask-limiter if you are using Python.

# ref
https://roadmap.sh/projects/weather-api-wrapper-service
https://www.visualcrossing.com/weather-api
