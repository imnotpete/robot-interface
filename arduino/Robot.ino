/*
  ESP8266 Blink by Simon Peter
  Blink the blue LED on the ESP-01 module
  This example code is in the public domain

  The blue LED on the ESP-01 module is connected to GPIO1
  (which is also the TXD pin; so we cannot use Serial.print() at the same time)

  Note that this sketch uses LED_BUILTIN to find the pin with the internal LED
*/

#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>
#include <ESP8266mDNS.h>

//LED_BUILTIN
//const int led = 5;

const int left1 = 4;
const int left2 = 5;
const int right1 = 14;
const int right2 = 10;


const char *ssid = "RonPaul2016";
const char *password = "nevergivethisout";

ESP8266WebServer server ( 80 );

void returnOK(String msg) {
  if (!msg) {
    msg = "test";
  }

  server.send(200, "text/html", msg);
}

void handleRoot() {
  Serial.println("request received");
  String html = "<!DOCTYPE html>\
<html>\
<head>\
<meta charset='UTF-8'>\
<title>Robot Control Panel</title>\
</head>\
<body style='zoom:5.0'>\
\
<table style='text-align:center'>\
\
<tr><td colspan='2'>\
<form action='/robot/forward' target='frame'><input type='submit' value='Forward'/></form>\
</td></tr>\
\
<tr><td>\
<form action='/robot/left' target='frame' style='float:left'><input type='submit' value='Left'/></form>\
</td><td>\
<form action='/robot/right' target='frame' style='float:right'><input type='submit' value='Right'/></form>\
</td></tr>\
\
<tr><td colspan='2'>\
<form action='/robot/reverse' target='frame'><input type='submit' value='Reverse'/></form>\
</td></tr>\
\
<tr><td colspan='2'>\
<form action='/robot/stop' target='frame'><input type='submit' value='Stop'/></form>\
</td></tr>\
</table>\
\
<iframe name='frame' style='border: 0px'></iframe>\
</body>\
</html>";

  returnOK(html);
}

void returnNotFound() {
  server.send(404, "text/plain", "");
}

void handleDirection(String path) {
  int index = path.lastIndexOf('/');
  String direction = path.substring(index + 1, path.length());
  Serial.println(direction);

  if (direction.equals("forward")) {
    //left motor forward
    digitalWrite(left1, HIGH);
    digitalWrite(left2, LOW);
    
    //right motor forward
    digitalWrite(right1, HIGH);
    digitalWrite(right2, LOW);
  } else if (direction.equals("reverse")) {
    //left motor reverse
    digitalWrite(left1, LOW);
    digitalWrite(left2, HIGH);
    
    //right motor reverse
    digitalWrite(right1, LOW);
    digitalWrite(right2, HIGH);
  } else if (direction.equals("left")) {
    //left motor reverse
    digitalWrite(left1, LOW);
    digitalWrite(left2, HIGH);
    
    //right motor forward
    digitalWrite(right1, HIGH);
    digitalWrite(right2, LOW);
  } else if (direction.equals("right")) {
    //left motor forward
    digitalWrite(left1, HIGH);
    digitalWrite(left2, LOW);
    
    //right motor reverse
    digitalWrite(right1, LOW);
    digitalWrite(right2, HIGH);
  } else if (direction.equals("stop")) {
    //left motor stop
    digitalWrite(left1, LOW);
    digitalWrite(left2, LOW);
    
    //right motor stop
    digitalWrite(right1, LOW);
    digitalWrite(right2, LOW);
  }
  Serial.println("finished direction change");
}

void setState(String side, String state) {
  if (side.equals("left")) {
    
  } else {
    
  }
}



void handleNotFound() {
  String path = server.uri();
  int index = path.indexOf("/robot");
  if (index >= 0) {
    handleDirection(path);
  }

  returnNotFound();
}

void setup() {
  Serial.begin(115200);
  delay(10);
  Serial.println();
  Serial.print("begin setup ");

  WiFi.begin ( ssid, password );
  // Wait for connection
  while ( WiFi.status() != WL_CONNECTED ) {
    delay ( 500 );
    Serial.print ( "." );
  }

  Serial.println ( "" );
  Serial.print ( "Connected to " );
  Serial.println ( ssid );
  Serial.print ( "IP address: " );
  Serial.println ( WiFi.localIP() );

  if ( MDNS.begin ( "esp8266" ) ) {
    Serial.println ( "MDNS responder started" );
  }

  server.on ( "/Robot.html", handleRoot );
  server.onNotFound ( handleNotFound );
  server.begin();
  Serial.println ( "HTTP server started" );

  //  Serial.println(pin);
    pinMode(left1, OUTPUT);     // Initialize the LED_BUILTIN pin as an output
    pinMode(left2, OUTPUT);     // Initialize the LED_BUILTIN pin as an output
    pinMode(right1, OUTPUT);     // Initialize the LED_BUILTIN pin as an output
    pinMode(right2, OUTPUT);     // Initialize the LED_BUILTIN pin as an output

  Serial.println("GPIO setup complete");
}

// the loop function runs over and over again forever
void loop() {

  server.handleClient();

  //  Serial.println("begin loop");
  //  digitalWrite(pin, LOW);   // Turn the LED on (Note that LOW is the voltage level
  //                                    // but actually the LED is on; this is because
  //                                    // it is acive low on the ESP-01)
  //  delay(1000);                      // Wait for a second
  //  digitalWrite(pin, HIGH);  // Turn the LED off by making the voltage HIGH
  //  delay(1000);                      // Wait for two seconds (to demonstrate the active low LED)
  //  Serial.println("end loop");
}

