package com.ardublock.translator.block.atto_fisica_bluetooth;


import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Atto_MRU_bluetooth extends TranslatorBlock
{
	public Atto_MRU_bluetooth(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException , SubroutineNotDeclaredException
	{
	   	TranslatorBlock liga_block = this.getRequiredTranslatorBlockAtSocket(0);
		String botao_liga = liga_block.toCode();


	    TranslatorBlock tb_dir = this.getRequiredTranslatorBlockAtSocket(1);
	    String ret_dir = tb_dir.toCode();
		String aux_ret_dir = "int motor_dir =";
		aux_ret_dir = aux_ret_dir + tb_dir.toCode();
		aux_ret_dir = aux_ret_dir + ";";
		
	   
	    TranslatorBlock tb_esq = this.getRequiredTranslatorBlockAtSocket(2);
	    String ret_esq = tb_esq.toCode();
		String aux_ret_esq = "int motor_esq =";
		aux_ret_esq = aux_ret_esq + tb_esq.toCode();
		aux_ret_esq = aux_ret_esq + ";";
				
		TranslatorBlock velocidade_block = this.getRequiredTranslatorBlockAtSocket(3);
		String potencio_block = velocidade_block.toCode();

		TranslatorBlock ultrassonic_block = this.getRequiredTranslatorBlockAtSocket(4);
		String ultra_block = ultrassonic_block.toCode();

		TranslatorBlock frequencia = this.getRequiredTranslatorBlockAtSocket(5);
			String string_freq = frequencia.toCode();
			int freq_int = Integer.parseInt(string_freq);
			double delay_double = 1.0/freq_int*1000;
			int delay_int = (int)delay_double;
			String delay_string = ""+(delay_int);
            
        TranslatorBlock m_bluetooth = this.getRequiredTranslatorBlockAtSocket(6);
	    String mbluetooth = m_bluetooth.toCode();
        int bluetooth_int = Integer.parseInt(mbluetooth);
        int port_blue = bluetooth_int+1;
        String m_blue_s = m_bluetooth.toCode();
        m_blue_s = m_blue_s + "," ;
        m_blue_s = m_blue_s + (port_blue);

		
		
		/*######################################################################
		###########constantes para o carrinho ir para frente ou para trás#######
		######################################################################*/
		
			String pino_frente = "HIGH";
			String pino5 = "5";
			String pino6 = "6";
			String variaveis_globais = " #include<SoftwareSerial.h> \n SoftwareSerial mySerial("+m_blue_s+"); \n double _ABVAR_1_Double = 0.0 ; \n bool _ABVAR_2_funcaoA= false; \n double _ABVAR_2_Distancia = 0.0; \n";
			String pin_vetor = "boolean __ardublockDigitalReadMRU(int pinNumber)\n { \n pinMode(pinNumber, INPUT);\n return digitalRead(pinNumber); \n }\n";
			String variaveis_glovais_2 = "bool _ABVAR_3_funcaoB= false ;\n int _ABVAR_4_estado = 0 ; \n double _ABVAR_5_Zerado = 0.0 ;\n";
			String vetor_ultrassonic = "int ardublockUltrasonicPing(int trigPin, int echoPin) \n { \n  int duration;\n pinMode(trigPin, OUTPUT);\n pinMode(echoPin, INPUT); \n digitalWrite(trigPin, LOW);\n  delayMicroseconds(2); \n  digitalWrite(trigPin, HIGH); \n delayMicroseconds(20);\n digitalWrite(trigPin, LOW);\n duration = pulseIn(echoPin, HIGH); \n if ((duration < 2) || (duration > 50000)) return false;\n return duration;\n } \n";
			String vetor_ultrassonic_2 = "float ardublockUltrasonicMesure(int trigPin, int echoPin, int mesure)\n{\n if (mesure==0){ \n    int duration=ardublockUltrasonicPing(trigPin, echoPin);\n    return (1.0*duration)*0.01715;\n }\n  else if(mesure==1){\n    float s1=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n    int t1=millis();\n    delay(50);\n    float s2=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n     int t2=millis();\n    return (s2-s1)/(1.0*(t2-t1)); \n  }\n  else if(mesure==2){\n  float s1=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t1=millis();\n  delay(50);\n   float s2=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t2=millis();\n   delay(50);\n  float s3=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t3=millis();\n  return (1.0*(s3-2.0*s2+s1))/((t3-t2)*(t2-t1));\n }\n else {\n return false;\n }\n}\n";
			String variaveis_globais_3 = "double _ABVAR_6_Tempo = 0.0 ;\n";
			String vetor_potenciometro = "int __ardublockAnalogReadMRU(int pinNumber)\n {\n   pinMode(pinNumber, INPUT);\n return analogRead(pinNumber);\n }\n";

		
		
			translator.addDefinitionCommand(variaveis_globais);
			translator.addDefinitionCommand(pin_vetor);
			translator.addDefinitionCommand(variaveis_glovais_2);
			translator.addDefinitionCommand(vetor_ultrassonic);
			translator.addDefinitionCommand(vetor_ultrassonic_2);
			translator.addDefinitionCommand(variaveis_globais_3);
			translator.addDefinitionCommand(vetor_potenciometro);
			translator.addDefinitionCommand(aux_ret_dir);
			translator.addDefinitionCommand(aux_ret_esq);
	    	translator.addSetupCommand("Serial.begin(9600);\n mySerial.begin(9600); \n pinMode(motor_dir, OUTPUT);\n  pinMode(motor_esq, OUTPUT);");

	    if(ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("9")){
			String ultra_block_echo = "9";
			String ultra_block_trig = "8";
			String ret_frente = "_ABVAR_1_Double = 0.0 ; \n _ABVAR_2_Distancia = ardublockUltrasonicMesure( "+ultra_block_trig+" , "+ultra_block_echo+" , 0 ); \n_ABVAR_2_funcaoA = __ardublockDigitalReadMRU("+ botao_liga +");\n if (( ( ( _ABVAR_2_funcaoA ) == ( HIGH ) ) && ( ( _ABVAR_3_funcaoB ) == ( LOW ) ) ))\n {\n_ABVAR_4_estado = ( 1 - _ABVAR_4_estado ) ;\n delay(500);\n}\n _ABVAR_3_funcaoB = _ABVAR_2_funcaoA ;\n if (( ( ( _ABVAR_4_estado ) == ( 1 ) ) && ( ( _ABVAR_2_Distancia ) > ( 10 ) ) )) \n { \n _ABVAR_5_Zerado = ardublockUltrasonicMesure( "+ultra_block_trig+" ," + ultra_block_echo + ", 0);\n Serial.println();\n Serial.println();\n Serial.print(\"Ajustado\"); \n Serial.print(\"\"); \nSerial.print(( _ABVAR_5_Zerado - _ABVAR_5_Zerado ));\n Serial.print(\"\");\n Serial.println();\n_ABVAR_1_Double = 0.0 ;\n_ABVAR_6_Tempo = millis();\nSerial.print(\"Tempo (ms)\");\nSerial.print(\"    ;    \");\n Serial.print(\"Distancia (cm)\");\n Serial.print(\"\");\nSerial.println();\n  while ( ( ( ( _ABVAR_4_estado ) == ( 1 ) ) && ( ( _ABVAR_2_Distancia ) > ( 10 ) ) ) )\n {\n if (__ardublockDigitalReadMRU("+ botao_liga +"))\n{\n_ABVAR_4_estado = 0;\n }\n _ABVAR_2_Distancia = ardublockUltrasonicMesure( "+ultra_block_trig+" , "+ultra_block_echo+" , 0 ); \n _ABVAR_1_Double = ( _ABVAR_5_Zerado - ardublockUltrasonicMesure( "+ultra_block_trig+" ,"+ ultra_block_echo+", 0 ));\nSerial.print(( millis() - _ABVAR_6_Tempo ));\nSerial.print(\"        ;         \");\n Serial.print(_ABVAR_1_Double);\n  mySerial.println(_ABVAR_1_Double);  \n Serial.print(\"\");\nSerial.println();\n digitalWrite(motor_dir,"+  pino_frente +");\n digitalWrite(motor_esq,"+ pino_frente +");\n  analogWrite("+ pino5 + ",map ( __ardublockAnalogReadMRU("+potencio_block+") , 0 , 1023 , 0 , 255 ) );\n analogWrite("+ pino6 + ",map ( __ardublockAnalogReadMRU("+potencio_block+") , 0 , 1023 , 0 , 255 ) );\n delay("+delay_string+");\n}\n}\n  _ABVAR_4_estado = 0.0 ;\n digitalWrite(motor_dir,HIGH);\n digitalWrite(motor_esq,HIGH);\n analogWrite(5, 0 ); \n analogWrite(6, 0 );\n delay(1);";
			return codePrefix + ret_frente + codeSuffix;
		}

		if(ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("7")){
			String ultra_block_echo = "7";
			String ultra_block_trig = "6";
			String ret_frente = "_ABVAR_1_Double = 0.0 ; \n _ABVAR_2_Distancia = ardublockUltrasonicMesure( "+ultra_block_trig+" , "+ultra_block_echo+" , 0 ); \n_ABVAR_2_funcaoA = __ardublockDigitalReadMRU("+ botao_liga +");\n if (( ( ( _ABVAR_2_funcaoA ) == ( HIGH ) ) && ( ( _ABVAR_3_funcaoB ) == ( LOW ) ) ))\n {\n_ABVAR_4_estado = ( 1 - _ABVAR_4_estado ) ;\n delay(500);\n}\n _ABVAR_3_funcaoB = _ABVAR_2_funcaoA ;\n if (( ( ( _ABVAR_4_estado ) == ( 1 ) ) && ( ( _ABVAR_2_Distancia ) > ( 10 ) ) )) \n { \n _ABVAR_5_Zerado = ardublockUltrasonicMesure( "+ultra_block_trig+" ," + ultra_block_echo + ", 0);\n Serial.println();\n Serial.println();\n Serial.print(\"Ajustado\"); \n Serial.print(\"\"); \nSerial.print(( _ABVAR_5_Zerado - _ABVAR_5_Zerado ));\n Serial.print(\"\");\n Serial.println();\n_ABVAR_1_Double = 0.0 ;\n_ABVAR_6_Tempo = millis();\nSerial.print(\"Tempo (ms)\");\nSerial.print(\"    ;    \");\n Serial.print(\"Distancia (cm)\");\n Serial.print(\"\");\nSerial.println();\n  while ( ( ( ( _ABVAR_4_estado ) == ( 1 ) ) && ( ( _ABVAR_2_Distancia ) > ( 10 ) ) ) )\n {\n if (__ardublockDigitalReadMRU("+ botao_liga +"))\n{\n_ABVAR_4_estado = 0;\n }\n _ABVAR_2_Distancia = ardublockUltrasonicMesure( "+ultra_block_trig+" , "+ultra_block_echo+" , 0 ); \n _ABVAR_1_Double = ( _ABVAR_5_Zerado - ardublockUltrasonicMesure( "+ultra_block_trig+" ,"+ ultra_block_echo+", 0 ));\nSerial.print(( millis() - _ABVAR_6_Tempo ));\nSerial.print(\"        ;         \");\n Serial.print(_ABVAR_1_Double); \n  mySerial.println(_ABVAR_1_Double);\n Serial.print(\"\");\nSerial.println();\n digitalWrite(motor_dir,"+  pino_frente +");\n digitalWrite(motor_esq,"+ pino_frente +");\n  analogWrite("+ pino5 + ",map ( __ardublockAnalogReadMRU("+potencio_block+") , 0 , 1023 , 0 , 255 ) );\n analogWrite("+ pino6 + ",map ( __ardublockAnalogReadMRU("+potencio_block+") , 0 , 1023 , 0 , 255 ) );\n delay("+delay_string+");\n}\n}\n  _ABVAR_4_estado = 0.0 ;\n digitalWrite(motor_dir,HIGH);\n digitalWrite(motor_esq,HIGH);\n analogWrite(5, 0 ); \n analogWrite(6, 0 );\n delay(1);";
			return codePrefix + ret_frente + codeSuffix;
		}

		if(ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("5")){
			String ultra_block_echo = "5";
			String ultra_block_trig = "4";
			String ret_frente = "_ABVAR_1_Double = 0.0 ; \n _ABVAR_2_Distancia = ardublockUltrasonicMesure( "+ultra_block_trig+" , "+ultra_block_echo+" , 0 ); \n_ABVAR_2_funcaoA = __ardublockDigitalReadMRU("+ botao_liga +");\n if (( ( ( _ABVAR_2_funcaoA ) == ( HIGH ) ) && ( ( _ABVAR_3_funcaoB ) == ( LOW ) ) ))\n {\n_ABVAR_4_estado = ( 1 - _ABVAR_4_estado ) ;\n delay(500);\n}\n _ABVAR_3_funcaoB = _ABVAR_2_funcaoA ;\n if (( ( ( _ABVAR_4_estado ) == ( 1 ) ) && ( ( _ABVAR_2_Distancia ) > ( 10 ) ) )) \n { \n _ABVAR_5_Zerado = ardublockUltrasonicMesure( "+ultra_block_trig+" ," + ultra_block_echo + ", 0);\n Serial.println();\n Serial.println();\n Serial.print(\"Ajustado\"); \n Serial.print(\"\"); \nSerial.print(( _ABVAR_5_Zerado - _ABVAR_5_Zerado ));\n Serial.print(\"\");\n Serial.println();\n_ABVAR_1_Double = 0.0 ;\n_ABVAR_6_Tempo = millis();\nSerial.print(\"Tempo (ms)\");\nSerial.print(\"    ;    \");\n Serial.print(\"Distancia (cm)\");\n Serial.print(\"\");\nSerial.println();\n  while ( ( ( ( _ABVAR_4_estado ) == ( 1 ) ) && ( ( _ABVAR_2_Distancia ) > ( 10 ) ) ) )\n {\n if (__ardublockDigitalReadMRU("+ botao_liga +"))\n{\n_ABVAR_4_estado = 0;\n }\n _ABVAR_2_Distancia = ardublockUltrasonicMesure( "+ultra_block_trig+" , "+ultra_block_echo+", 0 ); \n _ABVAR_1_Double = ( _ABVAR_5_Zerado - ardublockUltrasonicMesure( "+ultra_block_trig+" ,"+ ultra_block_echo+", 0 ));\nSerial.print(( millis() - _ABVAR_6_Tempo ));\nSerial.print(\"        ;         \");\n Serial.print(_ABVAR_1_Double);\n  mySerial.println(_ABVAR_1_Double); \nSerial.print(\"\");\nSerial.println();\n digitalWrite(motor_dir,"+  pino_frente +");\n digitalWrite(motor_esq,"+ pino_frente +");\n  analogWrite("+ pino5 + ",map ( __ardublockAnalogReadMRU("+potencio_block+") , 0 , 1023 , 0 , 255 ) );\n analogWrite("+ pino6 + ",map ( __ardublockAnalogReadMRU("+potencio_block+") , 0 , 1023 , 0 , 255 ) );\n delay("+delay_string+");\n}\n}\n  _ABVAR_4_estado = 0.0 ;\n digitalWrite(motor_dir,HIGH);\n digitalWrite(motor_esq,HIGH);\n analogWrite(5, 0 ); \n analogWrite(6, 0 );\n delay(1);";
			return codePrefix + ret_frente + codeSuffix;
		}
		if(ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("3")){
			String ultra_block_echo = "3";
			String ultra_block_trig = "2";
			String ret_frente = "_ABVAR_1_Double = 0.0 ; \n _ABVAR_2_Distancia = ardublockUltrasonicMesure( "+ultra_block_trig+" , "+ultra_block_echo+" , 0 ); \n_ABVAR_2_funcaoA = __ardublockDigitalReadMRU("+ botao_liga +");\n if (( ( ( _ABVAR_2_funcaoA ) == ( HIGH ) ) && ( ( _ABVAR_3_funcaoB ) == ( LOW ) ) ))\n {\n_ABVAR_4_estado = ( 1 - _ABVAR_4_estado ) ;\n delay(500);\n}\n _ABVAR_3_funcaoB = _ABVAR_2_funcaoA ;\n if (( ( ( _ABVAR_4_estado ) == ( 1 ) ) && ( ( _ABVAR_2_Distancia ) > ( 10 ) ) )) \n { \n _ABVAR_5_Zerado = ardublockUltrasonicMesure( "+ultra_block_trig+" ," + ultra_block_echo + ", 0);\n Serial.println();\n Serial.println();\n Serial.print(\"Ajustado\"); \n Serial.print(\"\"); \nSerial.print(( _ABVAR_5_Zerado - _ABVAR_5_Zerado ));\n Serial.print(\"\");\n Serial.println();\n_ABVAR_1_Double = 0.0 ;\n_ABVAR_6_Tempo = millis();\nSerial.print(\"Tempo (ms)\");\nSerial.print(\"    ;    \");\n Serial.print(\"Distancia (cm)\");\n Serial.print(\"\");\nSerial.println();\n  while ( ( ( ( _ABVAR_4_estado ) == ( 1 ) ) && ( ( _ABVAR_2_Distancia ) > ( 10 ) ) ) )\n {\n if (__ardublockDigitalReadMRU("+ botao_liga +"))\n{\n_ABVAR_4_estado = 0;\n }\n _ABVAR_2_Distancia = ardublockUltrasonicMesure( "+ultra_block_trig+" , "+ultra_block_echo+" , 0 ); \n _ABVAR_1_Double = ( _ABVAR_5_Zerado - ardublockUltrasonicMesure( "+ultra_block_trig+" ,"+ ultra_block_echo+", 0 ));\nSerial.print(( millis() - _ABVAR_6_Tempo ));\nSerial.print(\"        ;         \");\n Serial.print(_ABVAR_1_Double);\n  mySerial.println(_ABVAR_1_Double); \n Serial.print(\"\");\nSerial.println();\n digitalWrite(motor_dir,"+  pino_frente +");\n digitalWrite(motor_esq,"+ pino_frente +");\n  analogWrite("+ pino5 + ",map ( __ardublockAnalogReadMRU("+potencio_block+") , 0 , 1023 , 0 , 255 ) );\n analogWrite("+ pino6 + ",map ( __ardublockAnalogReadMRU("+potencio_block+") , 0 , 1023 , 0 , 255 ) );\n delay("+delay_string+");\n}\n}\n  _ABVAR_4_estado = 0.0 ;\n digitalWrite(motor_dir,HIGH);\n digitalWrite(motor_esq,HIGH);\n analogWrite(5, 0 ); \n analogWrite(6, 0 );\n delay(1);";
			return codePrefix + ret_frente + codeSuffix;
		}
			
		
		
		
		return codePrefix + "" + codeSuffix;
		
	}
}
