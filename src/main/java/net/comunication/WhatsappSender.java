package net.comunication;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class WhatsappSender {
	public static void main( String[] args )
	   {
			SendMessage( "918669011448",  "Radhe Radhe");
	   }
	
	public static void SendMessage(String phoneNumberTo, String message){
		
		try {
	           HttpRequest request = HttpRequest.newBuilder()
	               .uri(new URI("https://graph.facebook.com/v16.0/115903178177385/messages"))
	               .header("Authorization", "Bearer EAARyQko8o1sBAEceeYA5rI4cWO0pHTYAd1CZC9ZAsUYBNEFZCUlaqvY8qTZAIgAR5PyXbaUZBmwKCPqtZAJxojzSQ1mi6480yZAZAsgZA48B709dR2Du7ES0bMfF1SMVbI2dEUXj5OVVyUqvpDwGaY9RKAkxNC7ktTntPcdp6AkvxzyrMoJ9VZBiCXhPE8FlQ3kzbAveUxXZAhAswZDZD")
	               .header("Content-Type", "application/json")
	               .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": "+phoneNumberTo+", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
	               .build();
	           HttpClient http = HttpClient.newHttpClient();
	           HttpResponse<String> response = http.send(request,BodyHandlers.ofString());
	           System.out.println(response.body());
	          
	       } catch (URISyntaxException | IOException | InterruptedException e) {
	           e.printStackTrace();
	       }
	}
}