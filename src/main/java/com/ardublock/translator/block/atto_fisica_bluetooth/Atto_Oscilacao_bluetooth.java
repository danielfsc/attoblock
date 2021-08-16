package com.ardublock.translator.block.atto_fisica_bluetooth;


import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Atto_Oscilacao_bluetooth extends TranslatorBlock
{
	public Atto_Oscilacao_bluetooth(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException , SubroutineNotDeclaredException
	{
	   	TranslatorBlock zera_block_blu = this.getRequiredTranslatorBlockAtSocket(0);
		String botao_zera_blu = zera_block_blu.toCode();

		TranslatorBlock liga_block_blu = this.getRequiredTranslatorBlockAtSocket(1);
		String botao_liga_blu = liga_block_blu.toCode();

				
		TranslatorBlock ultrassonic_block_blu = this.getRequiredTranslatorBlockAtSocket(2);
		String ultra_block_blu = ultrassonic_block_blu.toCode();
		


		TranslatorBlock frequencia_blu = this.getRequiredTranslatorBlockAtSocket(3);
			String string_freq_blu = frequencia_blu.toCode();
			int freq_int_blu = Integer.parseInt(string_freq_blu);
			double delay_double_blu = 1.0/freq_int_blu*1000;
			int delay_int_blu = (int)delay_double_blu;
			String delay_string_blu = ""+(delay_int_blu);
            
            TranslatorBlock m_bluetooth = this.getRequiredTranslatorBlockAtSocket(4);
	    String mbluetooth = m_bluetooth.toCode();
        int bluetooth_int = Integer.parseInt(mbluetooth);
        int port_blue = bluetooth_int+1;
        String m_blue_s = m_bluetooth.toCode();
        m_blue_s = m_blue_s + "," ;
        m_blue_s = m_blue_s + (port_blue);
        
        
		
		

		

			String variaveis_globais = "#include <SoftwareSerial.h> \n SoftwareSerial mySerial("+m_blue_s+"); \n double _ABVAR_1_Decimal = 0.0 ; \n bool _ABVAR_2_funcaoA= false;\n bool botao_zera = LOW;\n boolean __ardublockDigitalRead(int pinNumber)\n {\n   pinMode(pinNumber, INPUT);\n   return digitalRead(pinNumber);\n }\n \n bool _ABVAR_3_funcaoB= false ;\n int _ABVAR_4_estado = 0 ;\n double _ABVAR_5_Zerado = 0.0;\n double _ABVAR_6_Tempo = 0.0 ;";
			String vetor_ultrassonic = "int ardublockUltrasonicPing(int trigPin, int echoPin) \n { \n  int duration;\n pinMode(trigPin, OUTPUT);\n pinMode(echoPin, INPUT); \n digitalWrite(trigPin, LOW);\n  delayMicroseconds(2); \n  digitalWrite(trigPin, HIGH); \n delayMicroseconds(20);\n digitalWrite(trigPin, LOW);\n duration = pulseIn(echoPin, HIGH); \n if ((duration < 2) || (duration > 50000)) return false;\n return duration;\n } \n";
			String vetor_ultrassonic_2 = "float ardublockUltrasonicMesure(int trigPin, int echoPin, int mesure)\n{\n if (mesure==0){ \n    int duration=ardublockUltrasonicPing(trigPin, echoPin);\n    return (1.0*duration)*0.01715;\n }\n  else if(mesure==1){\n    float s1=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n    int t1=millis();\n    delay(50);\n    float s2=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n     int t2=millis();\n    return (s2-s1)/(1.0*(t2-t1)); \n  }\n  else if(mesure==2){\n  float s1=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t1=millis();\n  delay(50);\n   float s2=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t2=millis();\n   delay(50);\n  float s3=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t3=millis();\n  return (1.0*(s3-2.0*s2+s1))/((t3-t2)*(t2-t1));\n }\n else {\n return false;\n }\n}\n";
			
			
		
		
			translator.addDefinitionCommand(variaveis_globais);
			translator.addDefinitionCommand(vetor_ultrassonic);
			translator.addDefinitionCommand(vetor_ultrassonic_2);
			translator.addSetupCommand("Serial.begin(9600);\n mySerial.begin(9600); \n");
		if(ultrassonic_block_blu.toCode().equals(ultra_block_blu) == ultrassonic_block_blu.toCode().equals("9")){
			String oscilacao_blu = "_ABVAR_1_Decimal = 0.0 ;\n   _ABVAR_2_funcaoA = __ardublockDigitalRead("+botao_liga_blu+") ;\n  botao_zera = __ardublockDigitalRead("+botao_zera_blu+") ;\n  if (( ( ( _ABVAR_2_funcaoA ) == ( HIGH ) ) && ( ( _ABVAR_3_funcaoB ) == ( LOW ) ) ))\n  {\n    _ABVAR_4_estado = ( 1 - _ABVAR_4_estado ) ;\n    delay( 500 );\n  }\n  _ABVAR_3_funcaoB = _ABVAR_2_funcaoA ;\n  if (botao_zera == HIGH)\n  {\n    _ABVAR_5_Zerado = ardublockUltrasonicMesure( 8 , "+ultra_block_blu +" , 0 )  ;\n    Serial.print(\"Ajustado\");\n    Serial.print(\" \");\n    Serial.print(( _ABVAR_5_Zerado - _ABVAR_5_Zerado ));\n    Serial.print(\"\");\n    Serial.println(\"\");\n    _ABVAR_1_Decimal = 0.0 ;\n    _ABVAR_6_Tempo = millis() ;\n    Serial.print(\"Tempo (ms)\");\n    Serial.print(\" \");\n    Serial.print(\"Distancia (cm)\");\n    Serial.print(\"\");\n    Serial.println();\n    delay( 1000 );\n  }\n    while ( ( ( _ABVAR_4_estado ) == ( 1 ) ) )\n    {\n      if (__ardublockDigitalRead("+botao_liga_blu+"))\n      {\n        _ABVAR_4_estado = 0 ;\n      }\n      _ABVAR_1_Decimal = ( _ABVAR_5_Zerado - ardublockUltrasonicMesure( 8 , "+ultra_block_blu +" , 0 )  ) ;\n      Serial.print(( millis() - _ABVAR_6_Tempo ));\n      Serial.print(\" \");\n      Serial.print(_ABVAR_1_Decimal);\n            mySerial.println(_ABVAR_1_Decimal);\n      Serial.print(\" \");\n      Serial.println(\"\");\n      delay("+delay_string_blu+" );\n    }\n \n    _ABVAR_4_estado = 0.0 ;\n \n";
			return codePrefix + oscilacao_blu + codeSuffix;	
		}
		if(ultrassonic_block_blu.toCode().equals(ultra_block_blu) == ultrassonic_block_blu.toCode().equals("7")){
			String oscilacao_blu = "_ABVAR_1_Decimal = 0.0 ;\n   _ABVAR_2_funcaoA = __ardublockDigitalRead("+botao_liga_blu+") ;\n  botao_zera = __ardublockDigitalRead("+botao_zera_blu+") ;\n  if (( ( ( _ABVAR_2_funcaoA ) == ( HIGH ) ) && ( ( _ABVAR_3_funcaoB ) == ( LOW ) ) ))\n  {\n    _ABVAR_4_estado = ( 1 - _ABVAR_4_estado ) ;\n    delay( 500 );\n  }\n  _ABVAR_3_funcaoB = _ABVAR_2_funcaoA ;\n  if (botao_zera == HIGH)\n  {\n    _ABVAR_5_Zerado = ardublockUltrasonicMesure( 6 , "+ultra_block_blu +" , 0 )  ;\n    Serial.print(\"Ajustado\");\n    Serial.print(\" \");\n    Serial.print(( _ABVAR_5_Zerado - _ABVAR_5_Zerado ));\n    Serial.print(\"\");\n    Serial.println(\"\");\n    _ABVAR_1_Decimal = 0.0 ;\n    _ABVAR_6_Tempo = millis() ;\n    Serial.print(\"Tempo (ms)\");\n    Serial.print(\" \");\n    Serial.print(\"Distancia (cm)\");\n    Serial.print(\"\");\n    Serial.println();\n    delay( 1000 );\n  }\n    while ( ( ( _ABVAR_4_estado ) == ( 1 ) ) )\n    {\n      if (__ardublockDigitalRead("+botao_liga_blu+")\n      {\n        _ABVAR_4_estado = 0 ;\n      }\n      _ABVAR_1_Decimal = ( _ABVAR_5_Zerado - ardublockUltrasonicMesure( 6 , "+ultra_block_blu +" , 0 )  ) ;\n      Serial.print(( millis() - _ABVAR_6_Tempo ));\n      Serial.print(\" \");\n      Serial.print(_ABVAR_1_Decimal);\n            mySerial.println(_ABVAR_1_Decimal);\n      Serial.print(\" \");\n      Serial.println(\"\");\n      delay("+delay_string_blu+" );\n    }\n \n    _ABVAR_4_estado = 0.0 ;\n \n";
			return codePrefix + oscilacao_blu + codeSuffix;		
		}
		if(ultrassonic_block_blu.toCode().equals(ultra_block_blu) == ultrassonic_block_blu.toCode().equals("5")){
			String oscilacao_blu = "_ABVAR_1_Decimal = 0.0 ;\n   _ABVAR_2_funcaoA = __ardublockDigitalRead("+botao_liga_blu+") ;\n  botao_zera = __ardublockDigitalRead("+botao_zera_blu+") ;\n  if (( ( ( _ABVAR_2_funcaoA ) == ( HIGH ) ) && ( ( _ABVAR_3_funcaoB ) == ( LOW ) ) ))\n  {\n    _ABVAR_4_estado = ( 1 - _ABVAR_4_estado ) ;\n    delay( 500 );\n  }\n  _ABVAR_3_funcaoB = _ABVAR_2_funcaoA ;\n  if (botao_zera == HIGH)\n  {\n    _ABVAR_5_Zerado = ardublockUltrasonicMesure( 4 , "+ultra_block_blu +" , 0 )  ;\n    Serial.print(\"Ajustado\");\n    Serial.print(\" \");\n    Serial.print(( _ABVAR_5_Zerado - _ABVAR_5_Zerado ));\n    Serial.print(\"\");\n    Serial.println(\"\");\n    _ABVAR_1_Decimal = 0.0 ;\n    _ABVAR_6_Tempo = millis() ;\n    Serial.print(\"Tempo (ms)\");\n    Serial.print(\" \");\n    Serial.print(\"Distancia (cm)\");\n    Serial.print(\"\");\n    Serial.println();\n    delay( 1000 );\n  }\n    while ( ( ( _ABVAR_4_estado ) == ( 1 ) ) )\n    {\n      if (__ardublockDigitalRead("+botao_liga_blu+")\n      {\n        _ABVAR_4_estado = 0 ;\n      }\n      _ABVAR_1_Decimal = ( _ABVAR_5_Zerado - ardublockUltrasonicMesure( 4 , "+ultra_block_blu +" , 0 )  ) ;\n      Serial.print(( millis() - _ABVAR_6_Tempo ));\n      Serial.print(\" \");\n      Serial.print(_ABVAR_1_Decimal);\n           mySerial.println(_ABVAR_1_Decimal);\n      Serial.print(\" \");\n      Serial.println(\"\");\n      delay("+delay_string_blu+" );\n    }\n \n    _ABVAR_4_estado = 0.0 ;\n \n";
			return codePrefix + oscilacao_blu + codeSuffix;		
		}
		if(ultrassonic_block_blu.toCode().equals(ultra_block_blu) == ultrassonic_block_blu.toCode().equals("3")){
			String oscilacao_blu = "_ABVAR_1_Decimal = 0.0 ;\n   _ABVAR_2_funcaoA = __ardublockDigitalRead("+botao_liga_blu+") ;\n  botao_zera = __ardublockDigitalRead("+botao_zera_blu+") ;\n  if (( ( ( _ABVAR_2_funcaoA ) == ( HIGH ) ) && ( ( _ABVAR_3_funcaoB ) == ( LOW ) ) ))\n  {\n    _ABVAR_4_estado = ( 1 - _ABVAR_4_estado ) ;\n    delay( 500 );\n  }\n  _ABVAR_3_funcaoB = _ABVAR_2_funcaoA ;\n  if (botao_zera == HIGH)\n  {\n    _ABVAR_5_Zerado = ardublockUltrasonicMesure( 2 , "+ultra_block_blu +" , 0 )  ;\n    Serial.print(\"Ajustado\");\n    Serial.print(\" \");\n    Serial.print(( _ABVAR_5_Zerado - _ABVAR_5_Zerado ));\n    Serial.print(\"\");\n    Serial.println(\"\");\n    _ABVAR_1_Decimal = 0.0 ;\n    _ABVAR_6_Tempo = millis() ;\n    Serial.print(\"Tempo (ms)\");\n    Serial.print(\" \");\n    Serial.print(\"Distancia (cm)\");\n    Serial.print(\"\");\n    Serial.println();\n    delay( 1000 );\n  }\n    while ( ( ( _ABVAR_4_estado ) == ( 1 ) ) )\n    {\n      if (__ardublockDigitalRead("+botao_liga_blu+")\n      {\n        _ABVAR_4_estado = 0 ;\n      }\n      _ABVAR_1_Decimal = ( _ABVAR_5_Zerado - ardublockUltrasonicMesure( 2 , "+ultra_block_blu +" , 0 )  ) ;\n      Serial.print(( millis() - _ABVAR_6_Tempo ));\n      Serial.print(\" \");\n      Serial.print(_ABVAR_1_Decimal);\n            mySerial.println(_ABVAR_1_Decimal);\n      Serial.print(\" \");\n      Serial.println(\"\");\n      delay("+delay_string_blu+" );\n    }\n \n    _ABVAR_4_estado = 0.0 ;\n \n";
			return codePrefix + oscilacao_blu + codeSuffix;		
		}
		return codePrefix + "" + codeSuffix;
	}
}
