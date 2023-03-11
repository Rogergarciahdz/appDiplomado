#include "BluetoothSerial.h"

#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run make menuconfig to and enable it
#endif

BluetoothSerial SerialBT;
#define  portPinCol1I  36
#define  portPinCol2I  39
#define portPinCol3I  34
#define portPinCol4I  35
#define portPinFil1I  32
#define portPinFil2I  33
#define portPinFil3I  25
#define portPinFil4I  26
int portValorCol1I = 0;
int portValorCol2I = 0;
int portValorCol3I = 0;
int portValorCol4I = 0;
int portValorFil1I = 0;
int portValorFil2I = 0;
int portValorFil3I = 0;
int portValorFil4I = 0;


#define portPinCol1D  2
#define portPinCol2D  4
#define portPinCol3D  5
#define portPinCol4D  8
#define portPinFil1D  19
#define portPinFil2D  21
#define portPinFil3D  22
#define portPinFil4D  23
int portValorCol1D = 0;
int portValorCol2D = 0;
int portValorCol3D = 0;
int portValorCol4D = 0;
int portValorFil1D = 0;
int portValorFil2D = 0;
int portValorFil3D = 0;
int portValorFil4D = 0;


boolean sendData=false;

void setup() {
Serial.begin(9600);
SerialBT.begin("Postura_diplomado");
Serial.println("El ESP32 está listo, puedes emparejarlo mediante Bluetooth");
delay(1000);

pinMode(portPinCol1I,INPUT);
pinMode(portPinCol2I,INPUT);
pinMode(portPinCol3I,INPUT);
pinMode(portPinCol4I,INPUT);
pinMode(portPinFil1I,INPUT);
pinMode(portPinFil2I,INPUT);
pinMode(portPinFil3I,INPUT);
pinMode(portPinFil4I,INPUT);



pinMode(portPinCol1D,INPUT);
pinMode(portPinCol2D,INPUT);
pinMode(portPinCol3D,INPUT);
pinMode(portPinCol4D,INPUT);
pinMode(portPinFil1D,INPUT);
pinMode(portPinFil2D,INPUT);
pinMode(portPinFil3D,INPUT);
pinMode(portPinFil4D,INPUT);

}

void loop() {
  if (Serial.available()>0) {
  char date = Serial.read();
  sendData=date=='1'? true : false;  
}
portValorCol1I =  analogRead (portPinCol1I);
portValorCol2I = analogRead (portPinCol2I);
portValorCol3I = analogRead (portPinCol3I);
portValorCol4I = analogRead (portPinCol4I);
portValorFil1I = analogRead (portPinFil1I);
portValorFil2I = analogRead (portPinFil2I);
portValorFil3I = analogRead (portPinFil3I);
portValorFil4I = analogRead (portPinFil4I);



portValorCol1D =  analogRead (portPinCol1D);
portValorCol2D = analogRead (portPinCol2D);
portValorCol3D = analogRead (portPinCol3D);
portValorCol4D = analogRead (portPinCol4D);
portValorFil1D = analogRead (portPinFil1D);
portValorFil2D = analogRead (portPinFil2D);
portValorFil3D = analogRead (portPinFil3D);
portValorFil4D = analogRead (portPinFil4D);



int portValorREs11 =  portValorFil1I + portValorCol1I;
//Serial.println(portValorREs11);
fflush(stdin);
int portValorREs12 =  portValorFil1I + portValorCol2I;
//Serial.println(portValorREs12);
fflush(stdin);
int portValorREs13 =  portValorFil1I + portValorCol3I;
fflush(stdin);
int portValorREs14 =  portValorFil1I + portValorCol4I;
fflush(stdin);
int portValorREs21 =  portValorFil2I + portValorCol1I;
fflush(stdin);
int portValorREs22 =  portValorFil2I + portValorCol2I;
fflush(stdin);
int portValorREs23 =  portValorFil2I + portValorCol3I;
fflush(stdin);
int portValorREs24 =  portValorFil2I + portValorCol4I;
fflush(stdin);
int portValorREs31 =  portValorFil3I + portValorCol1I;
fflush(stdin);
int portValorREs32 =  portValorFil3I + portValorCol2I;
fflush(stdin);
int portValorREs33 =  portValorFil3I + portValorCol3I;
fflush(stdin);
int portValorREs34 =  portValorFil3I + portValorCol4I;
fflush(stdin);
int portValorREs41 =  portValorFil4I + portValorCol1I;
fflush(stdin);
int portValorREs42 =  portValorFil4I + portValorCol2I;
fflush(stdin);
int portValorREs43 =  portValorFil4I + portValorCol3I;
fflush(stdin);
int portValorREs44 =  portValorFil4I + portValorCol4I;
fflush(stdin);
int mayor = 0 ;
int Fila = 0;

int portValorREs11D =  portValorFil1D + portValorCol1D;
int portValorREs12D =  portValorFil1D + portValorCol2D;
int portValorREs13D =  portValorFil1D + portValorCol3D;
int portValorREs14D =  portValorFil1D + portValorCol4D;
int portValorREs21D =  portValorFil2D + portValorCol1D;
int portValorREs22D =  portValorFil2D + portValorCol2D;
int portValorREs23D =  portValorFil2D + portValorCol3D;
int portValorREs24D =  portValorFil2D + portValorCol4D;
int portValorREs31D =  portValorFil3D + portValorCol1D;
int portValorREs32D =  portValorFil3D + portValorCol2D;
int portValorREs33D =  portValorFil3D + portValorCol3D;
int portValorREs34D =  portValorFil3D + portValorCol4D;
int portValorREs41D =  portValorFil4D + portValorCol1D;
int portValorREs42D =  portValorFil4D + portValorCol2D;
int portValorREs43D =  portValorFil4D + portValorCol3D;
int portValorREs44D =  portValorFil4D + portValorCol4D;
int mayorD = 0 ;
int FilaD = 0;




  int matriz [4] [4];
  matriz [0][0] = portValorREs11;
  fflush(stdin);
  matriz [0][1] = portValorREs12;
  fflush(stdin);
  matriz [0][2] = portValorREs13;
  fflush(stdin);
  matriz [0][3] = portValorREs14;
  fflush(stdin);
  matriz [1][0] = portValorREs21;
  fflush(stdin);
  matriz [1][1] = portValorREs22;
  fflush(stdin);
  matriz [1][2] = portValorREs23;
  fflush(stdin);
  matriz [1][3] = portValorREs24;
  fflush(stdin);
  matriz [2][0] = portValorREs31;
  fflush(stdin);
  matriz [2][1] = portValorREs32;
  fflush(stdin);
  matriz [2][2] = portValorREs33;
  fflush(stdin);
  matriz [2][3] = portValorREs34;
  fflush(stdin);
  matriz [3][0] = portValorREs41;
  fflush(stdin);
  matriz [3][1] = portValorREs42;
  fflush(stdin);
  matriz [3][2] = portValorREs43;
  fflush(stdin);
  matriz [3][3] = portValorREs44;
  fflush(stdin);
      Serial.println();
       for(int i =0; i<4; i++) {
         Serial.println();
           for(int j =0; j<4; j++) {
             Serial.print("\t");
             Serial.print(matriz[i][j]);
           int aux = matriz [i][j];
                  if (aux > mayor ) {
                    mayor = aux;
                    Fila = i;
                                      
                  }
           }
        }


int matrizD [4] [4];
  matrizD [0][0] = portValorREs11D;
  fflush(stdin);
  matrizD [0][1] = portValorREs12D;
  fflush(stdin);
  matrizD [0][2] = portValorREs13D;
  fflush(stdin);
  matrizD [0][3] = portValorREs14D;
  fflush(stdin);
  matrizD [1][0] = portValorREs21D;
  fflush(stdin);
  matrizD [1][1] = portValorREs22D;
  fflush(stdin);
  matrizD [1][2] = portValorREs23D;
  fflush(stdin);
  matrizD [1][3] = portValorREs24D;
  fflush(stdin);
  matrizD [2][0] = portValorREs31D;
  fflush(stdin);
  matrizD [2][1] = portValorREs32D;
  fflush(stdin);
  matrizD [2][2] = portValorREs33D;
  fflush(stdin);
  matrizD [2][3] = portValorREs34D;
  fflush(stdin);
  matrizD [3][0] = portValorREs41D;
  fflush(stdin);
  matrizD [3][1] = portValorREs42D;
  fflush(stdin);
  matrizD [3][2] = portValorREs43D;
  fflush(stdin);
  matrizD [3][3] = portValorREs44D;
  fflush(stdin);

      Serial.println();
       for(int a =0; a<4; a++) {
         Serial.println();
           for(int b =0; b<4; b++) {
              Serial.print("\t");
             Serial.print(matriz[a][b]);
           int auxD = matrizD [a][b];
                  if (auxD > mayorD ) {
                    mayorD = auxD;
                    FilaD = a;
                                      
                  }
           }
        }

        if (Fila == FilaD) {
         SerialBT.println("Posición correcta");
        }
        else if (Fila+1==FilaD || Fila-1==FilaD){
         SerialBT.println("Posición parcialmente incorrecta ");  
          }
        else if (Fila+2==FilaD || Fila-2==FilaD){
         SerialBT.println("Posición incorrecta ");  
          }
        else if(Fila==3&&FilaD==3){
         SerialBT.println("Posición incorrecta "); 
          }

       
if (Serial.available()) {
    SerialBT.write(Serial.read());
  }
  if (SerialBT.available()) {
    Serial.write(SerialBT.read());
    
  }
delay(5000);
 }