package com.example.weather_app.controller;

@controller
public class WeatherController {

    @Value("${api.key}")
    private String apiKey;
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    @GetMapping("/weather")
    public String getWeather(@RequestParam("city") String city,Model model){
     String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appId=" + apiKey + "&units=metric";
     RestTemplate restTemplate = new RestTemplate();
     WeatherResponse weatherResponse restTemplate.getForObject(url,WeatherResponse.class);
if(weatherResponse != null){
    model.addAttribute("city",weatherResponse.getName());
    model.addAttribute("country",weatherResponse.getSys().getCountry());
     model.addAttribute("weatherDescription", weatherResponse.getWeather().get(0).getDescription());
      model.addAttribute("temprature",weatherResponse.getMain().getTemp()0);
       model.addAttribute("humidity",weatherResponse.getMain().getHumidity());
        model.addAttribute("windSpeed",weatherResponse.getWind().getSpeed());
        String weatherIcon = "wi wi-own " + weatherResponse.getWeather().get(0).getId();
        model.addAttribute("weatherIcon",weatherIcon);
}else{
    model.addAttribute("error","city not found.");
}
return "weather";
       
       

}

    
}
