#include <Adafruit_NeoPixel.h>

const uint8_t dataPin = 7;
const uint8_t ledCount = 76;
const uint8_t brightness = 150; //out of 255
const uint8_t dim = 10;

Adafruit_NeoPixel strip = Adafruit_NeoPixel(ledCount, dataPin, NEO_GRB + NEO_KHZ800);

int state = 0, rando = random(0, 200), randoShift = random(0, ledCount), 
                                    spazzTime = random(20, 50), randoColor = 0;
int oBlueR = 0, oBlueG = 0, oBlueB = 255;
int blueR = 0, blueG = 0, blueB = 255;
int rainbowRGB[] = {0, 0, 0};
boolean spazzMode = false;

void setup() {
  strip.begin();
}

void loop() {
  
  state = digitalRead(7) + 2 * digitalRead(8) + 4 * digitalRead(9);
    
  switch (state) {
    case 0:
      reset();
      break;
    case 1:
      green();
      break;
    case 2:
      orangeBlueChase();
      break;
    case 3:
      rainbow();
      break;
    case 4:
      purple();
      break;
    default:
      reset();
      break;
  }

  strip.show();

}
void rainbow(){
  uint8_t time = millis() >> 2;

  for(uint16_t i = 0; i < ledCount; i++)
  {
    uint8_t p = time - i * 15;
    hsvToRgb( (uint32_t)p * 359 / 256, 255, brightness);
    strip.setPixelColor(i, strip.Color(rainbowRGB[0], rainbowRGB[1], rainbowRGB[2]) );
    
  }
  
}

void hsvToRgb(uint16_t h, uint8_t s, uint8_t v)
{
    uint8_t f = (h % 60) * 255 / 60;
    uint8_t p = (255 - s) * (uint16_t)v / 255;
    uint8_t q = (255 - f * (uint16_t)s / 255) * (uint16_t)v / 255;
    uint8_t t = (255 - (255 - f) * (uint16_t)s / 255) * (uint16_t)v / 255;
    uint8_t r = 0, g = 0, b = 0;
    switch((h / 60) % 6){
        case 0: r = v; g = t; b = p; break;
        case 1: r = q; g = v; b = p; break;
        case 2: r = p; g = v; b = t; break;
        case 3: r = p; g = q; b = v; break;
        case 4: r = t; g = p; b = v; break;
        case 5: r = v; g = p; b = q; break;
    }
    
    rainbowRGB[0] = r;
    rainbowRGB[1] = g;
    rainbowRGB[2] = b;
}

void purple(){
  
  for(int i=0;i<ledCount;i++){
    strip.setPixelColor( i, strip.Color(140, 8, 205) );
  }
  
}

void green(){
  
  for(int i=0;i<ledCount;i++){
    strip.setPixelColor( i, strip.Color(0, 255, 0) );
  }
  
}

int wow = 0, woah = ledCount;

void orangeBlueChase(){

  if (!spazzMode) {
    for (int i = 0; i < ledCount; i++)
      strip.setPixelColor( i, strip.Color(255, 32, 0) );
      
    for (int i = wow; i < wow + ledCount/4; i++) {
      if (i > ledCount)
        strip.setPixelColor( i - ledCount - 1, strip.Color(0, 0, 255) );
      else
        strip.setPixelColor( i, strip.Color(0, 0, 255) );
    }
    for (int i = woah; i < woah + ledCount/4; i++) {
      if (i > ledCount)
        strip.setPixelColor( i - ledCount - 1, strip.Color(0, 0, 255) );
      else
        strip.setPixelColor( i, strip.Color(0, 0, 255) );
    }
  } else {
    for (int i = 0; i < ledCount; i++)
      strip.setPixelColor(i, strip.Color(255, 32, 0) );
      
    for (int i = wow; i < wow + ledCount/4; i++) {
      if (i > ledCount)
        strip.setPixelColor( i - ledCount -  1, strip.Color(255, 255, 255) );
      else
        strip.setPixelColor( i, strip.Color(255, 255, 255) );
    }
    for (int i = woah; i < woah + ledCount/4; i++) {
      if (i > ledCount)
        strip.setPixelColor( i - ledCount - 1, strip.Color(255, 255, 255) );
      else
        strip.setPixelColor( i, strip.Color(255, 255, 255) );
    }  

    wow += randoShift;
    woah += randoShift;
    randoShift = random(0, ledCount);
    
    if (spazzTime == 0) {
      spazzMode = false;
      spazzTime = random(0, 8);
    } else {
      spazzTime --;
    }
    
    delay(100);
  }
  
  wow++;
  woah++;
  
  if (rando == 0) {
    spazzMode = true;
    rando = random(0, 200);
  } else
     rando--;
  
  if (wow > ledCount)
    wow -= ledCount + 1;
  if (woah > ledCount)
    woah -= ledCount + 1;
    
  delay(15);
}

void reset() {
    
    if (!spazzMode) {
    for (int i = 0; i < ledCount; i++)
      strip.setPixelColor( i, strip.Color(255, 32, 0) );
      
    for (int i = wow; i < wow + ledCount/4; i++) {
      if (i > ledCount)
        strip.setPixelColor( i - ledCount - 1, strip.Color(0, 0, 255) );
      else
        strip.setPixelColor( i, strip.Color(0, 0, 255) );
    }
    for (int i = woah; i < woah + ledCount/4; i++) {
      if (i > ledCount)
        strip.setPixelColor( i - ledCount - 1, strip.Color(0, 0, 255) );
      else
        strip.setPixelColor( i, strip.Color(0, 0, 255) );
    }
  } else {
    for (int i = 0; i < ledCount; i++)
      strip.setPixelColor(i, strip.Color(255, 32, 0) );
      
    for (int i = wow; i < wow + ledCount/4; i++) {
      if (i > ledCount)
        strip.setPixelColor( i - ledCount -  1, strip.Color(255, 255, 255) );
      else
        strip.setPixelColor( i, strip.Color(255, 255, 255) );
    }
    for (int i = woah; i < woah + ledCount/4; i++) {
      if (i > ledCount)
        strip.setPixelColor( i - ledCount - 1, strip.Color(255, 255, 255) );
      else
        strip.setPixelColor( i, strip.Color(255, 255, 255) );
    }  

    wow += randoShift;
    woah += randoShift;
    randoShift = random(0, ledCount);
    
    if (spazzTime == 0) {
      spazzMode = false;
      spazzTime = random(0, 8);
    } else {
      spazzTime --;
    }
    
    delay(100);
  }
  
  wow++;
  woah++;
  
  if (rando == 0) {
    spazzMode = true;
    rando = random(0, 200);
  } else
     rando--;
  
  if (wow > ledCount)
    wow -= ledCount + 1;
  if (woah > ledCount)
    woah -= ledCount + 1;
    
  delay(15);
    
}

