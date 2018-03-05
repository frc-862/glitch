#include <APA102.h>

const uint8_t dataPin = 11;
const uint8_t clockPin = 12;
const uint8_t ledCount = 76;
const uint8_t brightness = 19; //out of 31
const uint8_t dim = 10;

APA102<dataPin, clockPin> led;

rgb_color colors[ledCount];

int state = 0, rando = random(0, 200), randoShift = random(0, ledCount), spazzTime = random(20, 50), randoColor = 0;
int oBlueR = 0, oBlueG = 0, oBlueB = 255;
int blueR = 0, blueG = 0, blueB = 255;
boolean spazzMode = false;

void setup() {
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

}
void rainbow(){
  uint8_t time = millis() >> 2;

  for(uint16_t i = 0; i < ledCount; i++)
  {
    uint8_t p = time - i * 15;
    colors[i] = hsvToRgb((uint32_t)p * 359 / 256, 255, 255);
  }

  led.write(colors, ledCount, brightness);
}

rgb_color hsvToRgb(uint16_t h, uint8_t s, uint8_t v)
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
    return rgb_color(r, g, b);
}

void purple(){
  
  for(int i=0;i<ledCount;i++){
    colors[i]=rgb_color(140,8,205);
  }
  
 led.write(colors, ledCount, brightness);
}

void green(){
  for(int i=0;i<ledCount;i++){
    colors[i]=rgb_color(0,255,0);
  }
  
 led.write(colors, ledCount, brightness);
}

int wow = 0, woah = ledCount;

void orangeBlueChase(){

  if (!spazzMode) {
    for (int i = 0; i < ledCount; i++)
      colors[i] = rgb_color(255, 32, 0);
      
    for (int i = wow; i < wow + ledCount/4; i++) {
      if (i > ledCount)
        colors[i - ledCount - 1] = rgb_color(0, 0, 255);
      else
        colors[i] = rgb_color(0, 0, 255);
    }
    for (int i = woah; i < woah + ledCount/4; i++) {
      if (i > ledCount)
        colors[i - ledCount - 1] = rgb_color(0, 0, 255);
      else
        colors[i] = rgb_color(0, 0, 255);
    }
  } else {
    for (int i = 0; i < ledCount; i++)
      colors[i] = rgb_color(255, 32, 0);
      
    for (int i = wow; i < wow + ledCount/4; i++) {
      if (i > ledCount)
        colors[i - ledCount - 1] = rgb_color(255, 255, 255);
      else
        colors[i] = rgb_color(255, 255, 255);
    }
    for (int i = woah; i < woah + ledCount/4; i++) {
      if (i > ledCount)
        colors[i - ledCount - 1] = rgb_color(255, 255, 255);
      else
        colors[i] = rgb_color(255, 255, 255);
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
  led.write(colors, ledCount, brightness);
  delay(15);
}

void reset() {
    
    if (!spazzMode) {
    for (int i = 0; i < ledCount; i++)
      colors[i] = rgb_color(255, 32, 0);
      
    for (int i = wow; i < wow + ledCount/4; i++) {
      if (i > ledCount)
        colors[i - ledCount - 1] = rgb_color(0, 0, 255);
      else
        colors[i] = rgb_color(0, 0, 255);
    }
    for (int i = woah; i < woah + ledCount/4; i++) {
      if (i > ledCount)
        colors[i - ledCount - 1] = rgb_color(0, 0, 255);
      else
        colors[i] = rgb_color(0, 0, 255);
    }
  } else {
    for (int i = 0; i < ledCount; i++)
      colors[i] = rgb_color(255, 32, 0);
      
    for (int i = wow; i < wow + ledCount/4; i++) {
      if (i > ledCount)
        colors[i - ledCount - 1] = rgb_color(255, 255, 255);
      else
        colors[i] = rgb_color(255, 255, 255);
    }
    for (int i = woah; i < woah + ledCount/4; i++) {
      if (i > ledCount)
        colors[i - ledCount - 1] = rgb_color(255, 255, 255);
      else
        colors[i] = rgb_color(255, 255, 255);
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
  led.write(colors, ledCount, dim);
  delay(15);
}

