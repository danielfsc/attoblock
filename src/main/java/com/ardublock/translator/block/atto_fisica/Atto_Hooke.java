package com.ardublock.translator.block.atto_fisica;

// import com.ardublock.core.Context;
// import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Atto_Hooke extends TranslatorBlock {
	public Atto_Hooke(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException {
		TranslatorBlock zera_block = this.getRequiredTranslatorBlockAtSocket(0);
		String botao_zera = zera_block.toCode();

		TranslatorBlock liga_block = this.getRequiredTranslatorBlockAtSocket(1);
		String botao_liga = liga_block.toCode();

		TranslatorBlock ultrassonic_block = this.getRequiredTranslatorBlockAtSocket(2);
		String ultra_block = ultrassonic_block.toCode();

		TranslatorBlock frequencia = this.getRequiredTranslatorBlockAtSocket(3);
		String string_freq = frequencia.toCode();
		int freq_int = Integer.parseInt(string_freq);
		double delay_double = 1.0 / freq_int * 1000;
		int delay_int = (int) delay_double;
		String delay_string = "" + (delay_int);

		String variaveis_globais = "boolean __ardublockDigitalReadHooke(int pinNumber) \n {\n pinMode(pinNumber, INPUT);\n  return digitalRead(pinNumber);\n }\n double _ABVAR_1_Zerado = 0.0 ;\n double _ABVAR_2_Double = 0.0 ;\n";
		String vetor_ultrassonic = "int ardublockUltrasonicPing(int trigPin, int echoPin) \n { \n  int duration;\n pinMode(trigPin, OUTPUT);\n pinMode(echoPin, INPUT); \n digitalWrite(trigPin, LOW);\n  delayMicroseconds(2); \n  digitalWrite(trigPin, HIGH); \n delayMicroseconds(20);\n digitalWrite(trigPin, LOW);\n duration = pulseIn(echoPin, HIGH); \n if ((duration < 2) || (duration > 50000)) return false;\n return duration;\n } \n";
		String vetor_ultrassonic_2 = "float ardublockUltrasonicMesure(int trigPin, int echoPin, int mesure)\n{\n if (mesure==0){ \n    int duration=ardublockUltrasonicPing(trigPin, echoPin);\n    return (1.0*duration)*0.01715;\n }\n  else if(mesure==1){\n    float s1=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n    int t1=millis();\n    delay(50);\n    float s2=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n     int t2=millis();\n    return (s2-s1)/(1.0*(t2-t1)); \n  }\n  else if(mesure==2){\n  float s1=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t1=millis();\n  delay(50);\n   float s2=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t2=millis();\n   delay(50);\n  float s3=(1.0*ardublockUltrasonicPing(trigPin, echoPin))/5.7;\n  int t3=millis();\n  return (1.0*(s3-2.0*s2+s1))/((t3-t2)*(t2-t1));\n }\n else {\n return false;\n }\n}\n";

		translator.addDefinitionCommand(variaveis_globais);
		translator.addDefinitionCommand(vetor_ultrassonic);
		translator.addDefinitionCommand(vetor_ultrassonic_2);
		translator.addSetupCommand("Serial.begin(9600);\n Serial.println(\"Pressione o Botão Zerar\");\n ");
		if (ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("9")) {
			String lei_hooke = "if (__ardublockDigitalReadHooke(" + botao_zera
					+ "))\n {\n  _ABVAR_1_Zerado = ardublockUltrasonicMesure( 8 , " + ultra_block
					+ " , 0 )  ;\n Serial.print(\"Ajustado\");\n Serial.print(\" \"); \n Serial.print(( _ABVAR_1_Zerado - _ABVAR_1_Zerado ));\n Serial.print(\" \");\n  Serial.println();\n delay("
					+ delay_string + ");\n }\n if (__ardublockDigitalReadHooke(" + botao_liga
					+ ")) \n{\n  _ABVAR_2_Double = ( _ABVAR_1_Zerado - ardublockUltrasonicMesure( 8 , " + ultra_block
					+ " , 0 )  ) ; \nSerial.print(_ABVAR_2_Double);\nSerial.print(\"\");\nSerial.print(\"cm\");\nSerial.print(\"\");\nSerial.println(\"\");\n delay( "
					+ delay_string + " );\n}\n";
			return codePrefix + lei_hooke + codeSuffix;
		}
		if (ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("7")) {
			String lei_hooke = "if (__ardublockDigitalReadHooke(" + botao_zera
					+ "))\n {\n  _ABVAR_1_Zerado = ardublockUltrasonicMesure( 6 , " + ultra_block
					+ " , 0 )  ;\n Serial.print(\"Ajustado\");\n Serial.print(\" \"); \n Serial.print(( _ABVAR_1_Zerado - _ABVAR_1_Zerado ));\n Serial.print(\" \");\n  Serial.println();\n delay("
					+ delay_string + ");\n }\n if (__ardublockDigitalReadHooke(" + botao_liga
					+ ")) \n{\n  _ABVAR_2_Double = ( _ABVAR_1_Zerado - ardublockUltrasonicMesure( 6 , " + ultra_block
					+ " , 0 )  ) ; \nSerial.print(_ABVAR_2_Double);\nSerial.print(\"\");\nSerial.print(\"cm\");\nSerial.print(\"\");\nSerial.println(\"\");\n delay( "
					+ delay_string + " );\n}\n";
			return codePrefix + lei_hooke + codeSuffix;
		}
		if (ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("5")) {
			String lei_hooke = "if (__ardublockDigitalReadHooke(" + botao_zera
					+ "))\n {\n  _ABVAR_1_Zerado = ardublockUltrasonicMesure( 4 , " + ultra_block
					+ " , 0 )  ;\n Serial.print(\"Ajustado\");\n Serial.print(\" \"); \n Serial.print(( _ABVAR_1_Zerado - _ABVAR_1_Zerado ));\n Serial.print(\" \");\n  Serial.println();\n delay("
					+ delay_string + ");\n }\n if (__ardublockDigitalReadHooke(" + botao_liga
					+ ")) \n{\n  _ABVAR_2_Double = ( _ABVAR_1_Zerado - ardublockUltrasonicMesure( 4 , " + ultra_block
					+ " , 0 )  ) ; \nSerial.print(_ABVAR_2_Double);\nSerial.print(\"\");\nSerial.print(\"cm\");\nSerial.print(\"\");\nSerial.println(\"\");\n delay( "
					+ delay_string + " );\n}\n";
			return codePrefix + lei_hooke + codeSuffix;
		}
		if (ultrassonic_block.toCode().equals(ultra_block) == ultrassonic_block.toCode().equals("3")) {
			String lei_hooke = "if (__ardublockDigitalReadHooke(" + botao_zera
					+ "))\n {\n  _ABVAR_1_Zerado = ardublockUltrasonicMesure( 2 , " + ultra_block
					+ " , 0 )  ;\n Serial.print(\"Ajustado\");\n Serial.print(\" \"); \n Serial.print(( _ABVAR_1_Zerado - _ABVAR_1_Zerado ));\n Serial.print(\" \");\n  Serial.println();\n delay("
					+ delay_string + ");\n }\n if (__ardublockDigitalReadHooke(" + botao_liga
					+ ")) \n{\n  _ABVAR_2_Double = ( _ABVAR_1_Zerado - ardublockUltrasonicMesure( 2 , " + ultra_block
					+ " , 0 )  ) ; \nSerial.print(_ABVAR_2_Double);\nSerial.print(\"\");\nSerial.print(\"cm\");\nSerial.print(\"\");\nSerial.println(\"\");\n delay( "
					+ delay_string + " );\n}\n";
			return codePrefix + lei_hooke + codeSuffix;
		}
		return codePrefix + "" + codeSuffix;
	}
}
