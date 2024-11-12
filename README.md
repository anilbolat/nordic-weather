# nordic-weather

In this project, we will build a weather API that fetches and returns weather data from a 3rd party API. This project
will help you understand how to work with 3rd party APIs, caching, and environment variables.

# 3rd party weather api
- https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/[location]/[date1]/[date2]?key=YOUR_API_KEY
- Dates should be in the format yyyy-MM-dd. For example 2020-10-19 for October 19th, 2020 or 2017-02-03 for February 3rd, 2017.


# remaining tasks
- Make sure to handle errors properly. 
  - if cache connection is down,
  - 
- Implement rate limiting to prevent abuse of your API. You can use a package like express-rate-limit if you are using Node.js or flask-limiter if you are using Python.

# ref
- https://roadmap.sh/projects/weather-api-wrapper-service
- https://www.visualcrossing.com/weather-api
