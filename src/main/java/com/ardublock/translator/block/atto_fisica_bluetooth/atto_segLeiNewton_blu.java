package com.ardublock.translator.block.atto_fisica_bluetooth;


import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class atto_segLeiNewton_blu extends TranslatorBlock
{
	public atto_segLeiNewton_blu(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException , SubroutineNotDeclaredException
	{
	   	TranslatorBlock liga_block = this.getRequiredTranslatorBlockAtSocket(0);
		String botao_liga = liga_block.toCode();


		TranslatorBlock ultrassonic_block = this.getRequiredTranslatorBlockAtSocket(1);
		String ultra_block = ultrassonic_block.toCode();
        
        TranslatorBlock distancia_block = this.getRequiredTranslatorBlockAtSocket(2);
		String dist_block = distancia_block.toCode();
        
        TranslatorBlock m_bluetooth = this.getRequiredTranslatorBlockAtSocket(3);
	    String mbluetooth = m_bluetooth.toCode();
        int bluetooth_int = Integer.parseInt(mbluetooth);
        int port_blue = bluetooth_int+1;
        String m_blue_s = m_bluetooth.toCode();
        m_blue_s = m_blue_s + "," ;
        m_blue_s = m_blue_s + (port_blue);


		
		/*######################################################################
		###########constantes para o carrinho ir para frente ou para trás#######
		######################################################################*/
		

			String variaveis_globais = "#include<SoftwareSerial.h> \n SoftwareSerial mySerial("+m_blue_s+"); \n double Decimal = 0.0; \n bool funcaoA_newton= false ; \n";
			String pin_vetor = "boolean botao_Digital(int pinNumber)\n { \n pinMode(pinNumber, INPUT); \n return digitalRead(pinNumber); \n } \n";
			String variaveis_glovais_2 = "bool funcaoB_Newton= false ; \n int estado_newton = 0 ; \n double zerado_newton = 0.0 ; \n";
			String vetor_ultrassonic = "int ardublockUltrasonicPing(int trigPin, int echoPin) \n { \n  int duration;\n pinMode(trigPin, OUTPUT);\n pinMode(echoPin, INPUT); \n digitalWrite(trigPin, LOW);\n  delayMicroseconds(2); \n  digitalWrite(trigPin, HIGH); \n delayMicroseconds(20);\n digitalWrite(trigPin, LOW);\n duration = pulseIn(echoPin, HIGH); \n if ((duration < 2) || (duration > 50000)) return false;\n return duration;\n } \n";
			String vetor_ultrassonic_2 = "float ardublockUltrasonicMesure(int trigPin, int echoPin, int mesure)\n{\n if (mesure==0){ \n    int duration=ardublockUltrasonicPing(trigPin, echoPin);\n    return (1.0*duration)*0.01715;\n }\n  else if(mesure==1){\n    float s1=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n    int t1=millis();\n    delay(50);\n    float s2=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n     int t2=millis();\n    return (s2-s1)/(1.0*(t2-t1)); \n  }\n  else if(mesure==2){\n  float s1=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t1=millis();\n  delay(50);\n   float s2=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t2=millis();\n   delay(50);\n  float s3=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t3=millis();\n  return (1.0*(s3-2.0*s2+s1))/((t3-t2)*(t2-t1));\n }\n else {\n return false;\n }\n}\n";
			String variaveis_globais_3 = "double _ABVAR_6_Tempo = 0.0 ;\n";
			

		
		
			translator.addDefinitionCommand(variaveis_globais);
			translator.addDefinitionCommand(pin_vetor);
			translator.addDefinitionCommand(variaveis_glovais_2);
			translator.addDefinitionCommand(vetor_ultrassonic);
			translator.addDefinitionCommand(vetor_ultrassonic_2);
			translator.addDefinitionCommand(variaveis_globais_3);

	    	translator.addSetupCommand("Serial.begin(9600);\n mySerial.begin(9600); \n Serial.println(\"Aperte o Botão para iniciar a obtencao de dados\");\n");

	    if(ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("9")){
			String ultra_block_echo = "9";
			String ultra_block_trig = "8";
			String ret_frente = "Decimal = 0.0 ;\n   funcaoA_newton = botao_Digital("+ botao_liga +") ;\n   if (( ( ( funcaoA_newton ) == ( HIGH ) ) && ( ( funcaoB_Newton ) == ( LOW ) ) ))\n  {\n    estado_newton = ( 1 - estado_newton ) ;\n    delay( 500 );\n  }\n  funcaoB_Newton = funcaoA_newton ;\n  if (( ( ( estado_newton ) == ( 1 ) ) && ( ( Decimal ) < ( "+ dist_block +" ) ) ))\n  {\n    zerado_newton = ardublockUltrasonicMesure( "+ ultra_block_trig +" , "+ ultra_block_echo +" , 0 )  ;\n    Serial.print(\"Ajustado\");\n    Serial.print(\" \");\n    Serial.print(( zerado_newton - zerado_newton ));\n    Serial.print(\" \");\n    Serial.println();\n    Decimal = 0.0 ;\n    _ABVAR_6_Tempo = millis() ;\n    Serial.print(\"Tempo (s)\");\n    Serial.print(\" \");\n    Serial.print(\"Distancia (cm)\");\n    Serial.print(\" \");\n    Serial.println();\n    while ( ( ( ( estado_newton ) == ( 1 ) ) && ( ( Decimal ) < ( "+ dist_block +" ) ) ) )\n    {\n      if (botao_Digital("+ botao_liga +"))\n      {\n        estado_newton = 0 ;\n      }\n      Decimal = ( ardublockUltrasonicMesure( "+ ultra_block_trig +" , "+ ultra_block_echo+" , 0 )  - zerado_newton ) ;\n  if ((Decimal > 1) && (Decimal < "+ dist_block +"  )) { \n    Serial.print(( millis() - _ABVAR_6_Tempo )/1000);\n      Serial.print(\" \");\n  Serial.print(\"  ;  \");\n      Serial.print(Decimal);\n mySerial.println(Decimal);\n      Serial.print(\" \");\n      Serial.println();\n  } \n  }\n \n  }\n   estado_newton = 0.0 ;\n";
			return codePrefix + ret_frente + codeSuffix;
		}

		if(ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("7")){
			String ultra_block_echo = "7";
			String ultra_block_trig = "6";
			String ret_frente = "Decimal = 0.0 ;\n   funcaoA_newton = botao_Digital("+ botao_liga +") ;\n   if (( ( ( funcaoA_newton ) == ( HIGH ) ) && ( ( funcaoB_Newton ) == ( LOW ) ) ))\n  {\n    estado_newton = ( 1 - estado_newton ) ;\n    delay( 500 );\n  }\n  funcaoB_Newton = funcaoA_newton ;\n  if (( ( ( estado_newton ) == ( 1 ) ) && ( ( Decimal ) < ("+ dist_block +" ) ) ))\n  {\n    zerado_newton = ardublockUltrasonicMesure( "+ ultra_block_trig +" , "+ ultra_block_echo +" , 0 )  ;\n    Serial.print(\"Ajustado\");\n    Serial.print(\" \");\n    Serial.print(( zerado_newton - zerado_newton ));\n    Serial.print(\" \");\n    Serial.println();\n    Decimal = 0.0 ;\n    _ABVAR_6_Tempo = millis() ;\n    Serial.print(\"Tempo (s)\");\n    Serial.print(\" \");\n    Serial.print(\"Distancia (cm)\");\n    Serial.print(\" \");\n    Serial.println();\n    while ( ( ( ( estado_newton ) == ( 1 ) ) && ( ( Decimal ) < ( "+ dist_block +" ) ) ) )\n    {\n      if (botao_Digital("+ botao_liga +"))\n      {\n        estado_newton = 0 ;\n      }\n      Decimal = ( ardublockUltrasonicMesure( "+ ultra_block_trig +" , "+ ultra_block_echo+" , 0 )  - zerado_newton ) ;\n  if ((Decimal > 1) && (Decimal < "+ dist_block +"  )) { \n    Serial.print(( millis() - _ABVAR_6_Tempo )/1000);\n      Serial.print(\" \");\n  Serial.print(\"  ;  \");\n      Serial.print(Decimal);\n mySerial.println(Decimal); \n      Serial.print(\" \");\n      Serial.println();\n  } \n  }\n \n  }\n   estado_newton = 0.0 ;\n";
			return codePrefix + ret_frente + codeSuffix;
		}

		if(ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("5")){
			String ultra_block_echo = "5";
			String ultra_block_trig = "4";
			String ret_frente = "Decimal = 0.0 ;\n   funcaoA_newton = botao_Digital("+ botao_liga +") ;\n   if (( ( ( funcaoA_newton ) == ( HIGH ) ) && ( ( funcaoB_Newton ) == ( LOW ) ) ))\n  {\n    estado_newton = ( 1 - estado_newton ) ;\n    delay( 500 );\n  }\n  funcaoB_Newton = funcaoA_newton ;\n  if (( ( ( estado_newton ) == ( 1 ) ) && ( ( Decimal ) < ( "+ dist_block +" ) ) ))\n  {\n    zerado_newton = ardublockUltrasonicMesure( "+ ultra_block_trig +" , "+ ultra_block_echo +" , 0 )  ;\n    Serial.print(\"Ajustado\");\n    Serial.print(\" \");\n    Serial.print(( zerado_newton - zerado_newton ));\n    Serial.print(\" \");\n    Serial.println();\n    Decimal = 0.0 ;\n    _ABVAR_6_Tempo = millis() ;\n    Serial.print(\"Tempo (s)\");\n    Serial.print(\" \");\n    Serial.print(\"Distancia (cm)\");\n    Serial.print(\" \");\n    Serial.println();\n    while ( ( ( ( estado_newton ) == ( 1 ) ) && ( ( Decimal ) < ( "+ dist_block +" ) ) ) )\n    {\n      if (botao_Digital("+ botao_liga +"))\n      {\n        estado_newton = 0 ;\n      }\n      Decimal = ( ardublockUltrasonicMesure( "+ ultra_block_trig +" , "+ ultra_block_echo+" , 0 )  - zerado_newton ) ;\n  if ((Decimal > 1) && (Decimal < "+ dist_block +"  )) { \n    Serial.print(( millis() - _ABVAR_6_Tempo )/1000);\n      Serial.print(\" \");\n  Serial.print(\"  ;  \");\n      Serial.print(Decimal);\n mySerial.println(Decimal);\n     Serial.print(\" \");\n      Serial.println();\n  } \n  }\n \n  }\n   estado_newton = 0.0 ;\n";
			return codePrefix + ret_frente + codeSuffix;
		}
		if(ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("3")){
			String ultra_block_echo = "3";
			String ultra_block_trig = "2";
			String ret_frente = "Decimal = 0.0 ;\n   funcaoA_newton = botao_Digital("+ botao_liga +") ;\n   if (( ( ( funcaoA_newton ) == ( HIGH ) ) && ( ( funcaoB_Newton ) == ( LOW ) ) ))\n  {\n    estado_newton = ( 1 - estado_newton ) ;\n    delay( 500 );\n  }\n  funcaoB_Newton = funcaoA_newton ;\n  if (( ( ( estado_newton ) == ( 1 ) ) && ( ( Decimal ) < ( "+ dist_block +" ) ) ))\n  {\n    zerado_newton = ardublockUltrasonicMesure( "+ ultra_block_trig +" , "+ ultra_block_echo +" , 0 )  ;\n    Serial.print(\"Ajustado\");\n    Serial.print(\" \");\n    Serial.print(( zerado_newton - zerado_newton ));\n    Serial.print(\" \");\n    Serial.println();\n    Decimal = 0.0 ;\n    _ABVAR_6_Tempo = millis() ;\n    Serial.print(\"Tempo (s)\");\n    Serial.print(\" \");\n    Serial.print(\"Distancia (cm)\");\n    Serial.print(\" \");\n    Serial.println();\n    while ( ( ( ( estado_newton ) == ( 1 ) ) && ( ( Decimal ) < ( "+ dist_block +" ) ) ) )\n    {\n      if (botao_Digital("+ botao_liga +"))\n      {\n        estado_newton = 0 ;\n      }\n      Decimal = ( ardublockUltrasonicMesure( "+ ultra_block_trig +" , "+ ultra_block_echo+" , 0 )  - zerado_newton ) ;\n  if ((Decimal > 1) && (Decimal < "+ dist_block +"  )) { \n    Serial.print(( millis() - _ABVAR_6_Tempo )/1000);\n      Serial.print(\" \");\n  Serial.print(\"  ;  \");\n      Serial.print(Decimal);\n  mySerial.println(Decimal);\n    Serial.print(\" \");\n      Serial.println();\n  } \n  }\n \n  }\n   estado_newton = 0.0 ;\n";
			return codePrefix + ret_frente + codeSuffix;
		}
			
		
		
		
		return codePrefix + "" + codeSuffix;
		
	}
}
