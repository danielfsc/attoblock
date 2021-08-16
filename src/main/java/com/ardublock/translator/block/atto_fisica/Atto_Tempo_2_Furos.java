package com.ardublock.translator.block.atto_fisica;


import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Atto_Tempo_2_Furos extends TranslatorBlock
{
	public Atto_Tempo_2_Furos(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException , SubroutineNotDeclaredException
	{
	   	TranslatorBlock zera_block = this.getRequiredTranslatorBlockAtSocket(0);
		String botao_iniciar = zera_block.toCode();

		TranslatorBlock liga_block = this.getRequiredTranslatorBlockAtSocket(1);
		String Led_atto = liga_block.toCode();

				
		TranslatorBlock ultrassonic_block = this.getRequiredTranslatorBlockAtSocket(2);
		String sensor_LDR = ultrassonic_block.toCode();
		



		

			String variaveis_globais = "int estado = 0 ; \n";
			String vetor_interrupcao= "void interrupcao() { \n estado = ( 1 - estado ); \n }\n";
            String vetor_LDR = "int __ardublockAnalogReadTempo(int pinNumber)\n{\n  pinMode(pinNumber, INPUT);\n return \n analogRead(pinNumber);\n } \n ";
            
			 
			
		if(zera_block.toCode().equals(botao_iniciar) == zera_block.toCode().equals("2")){
            translator.addDefinitionCommand(variaveis_globais);
			translator.addDefinitionCommand(vetor_interrupcao);
			translator.addDefinitionCommand(vetor_LDR);
			translator.addSetupCommand("pinMode( "+ Led_atto +", OUTPUT); \n Serial.begin(9600); \n pinMode(2, INPUT_PULLUP); \n  attachInterrupt(0, interrupcao, RISING); \n Serial.println(\"Aperte no Botao para iniciar a obtencao dos dados\"); \n  Serial.println();\n");
            
            String atto_tempo_2 = "bool auxiliarmsg = HIGH; \n bool teste1 = HIGH; \nbool mensagemfinal = LOW;\n bool AtivadorEncerra = LOW;\n  float tempoauxiliar = 0 ;\n  int ativador = 0 ;\n  int ativador2 = 0 ;\n  int ativador3 = 0 ;\n  float TempoFinal = 0 ;\n  int AtivadorClaro = 0 ;\n  int _AtivadorEscuro = 1 ;\n\n  digitalWrite("+Led_atto+" , HIGH );\n\n\n  while ( ( ( estado ) == ( 1 ) ) )\n  {\n \n    while ( ( ( auxiliarmsg ) == ( HIGH ) ) )\n    {\n      Serial.print(\"pronto para iniciar ...\");\n Serial.print(\"\"); \n     Serial.println();\n auxiliarmsg = LOW ;\n      AtivadorEncerra = HIGH;\n    }\n \n    tempoauxiliar = millis() ;\n    if (( ( ( __ardublockAnalogReadTempo("+sensor_LDR+") ) == ( 0 ) ) && ( ( teste1 ) == ( HIGH ) ) ))\n    {\n   ativador = ( ativador + 1 ) ;\n  }\n    while ( ( ( ativador ) == ( 1 ) ) )\n    {\n      ativador = ( ativador + 1 ) ;\n      ativador2 = 1 ;\n      teste1 = LOW ;\n      delay( 500 );\n    }\n\n    if (( ( ( ativador2 ) == ( 1 ) ) && ( ( __ardublockAnalogReadTempo("+sensor_LDR+") ) >= ( 3 ) ) ))\n    {\n      ativador3 = 1 ;\n      ativador2 = ( ativador2 + 1 ) ;\n    }\n    while ( ( ( ativador3 ) == ( 1 ) ) )\n    {\n      TempoFinal = ( millis() - tempoauxiliar ) ;\n      _AtivadorEscuro = 1 ;\n      while ( ( ( ( __ardublockAnalogReadTempo("+sensor_LDR+") ) == ( 0 ) ) && ( ( _AtivadorEscuro ) == ( 1 ) ) ) )\n      {\n        AtivadorClaro = 1 ;\n        _AtivadorEscuro = ( _AtivadorEscuro + 1 ) ;\n      }\n \n      while ( ( ( ( __ardublockAnalogReadTempo("+sensor_LDR+") ) >= ( 3 ) ) && ( ( AtivadorClaro ) == ( 1 ) ) ) )\n      {\n        ativador2 = ( ativador2 + 1 ) ;\n        AtivadorClaro = ( AtivadorClaro + 1 ) ;\n        mensagemfinal = HIGH ;\n      }\n \n      if (( ( mensagemfinal ) == ( HIGH ) ))\n       {\n         Serial.print(\"Tempo:\");\n   Serial.print(\" \");\n        Serial.print(TempoFinal / 1000);\n         Serial.print(\" segundos\");\n        Serial.println();\n        ativador3 = ( ativador3 + 1 ) ;\n   Serial.println();\n          Serial.println(\"Concluido\");\n          Serial.println(\"Aperte no Botão para finalizar essa contagem\");\n           \n      mensagemfinal = LOW ;\n \n  }\n    }\n \n   }\n \n  if ( (AtivadorEncerra == HIGH) && ( estado == 0)) {\n    Serial.println(\"Finalizado.\");\n   } \n";
            
            return codePrefix + atto_tempo_2 + codeSuffix;
        }
		
        if(zera_block.toCode().equals(botao_iniciar) == zera_block.toCode().equals("3")){
                   translator.addDefinitionCommand(variaveis_globais);
			translator.addDefinitionCommand(vetor_interrupcao);
			translator.addDefinitionCommand(vetor_LDR);
			translator.addSetupCommand("pinMode( "+ Led_atto +", OUTPUT); \n Serial.begin(9600); \n pinMode(3, INPUT_PULLUP); \n  attachInterrupt(1, interrupcao, RISING); \n Serial.println(\"Aperte no Botao para iniciar a obtencao dos dados\"); \n  Serial.println();\n");
            
            String atto_tempo_2 = "bool auxiliarmsg = HIGH; \n bool teste1 = HIGH; \nbool mensagemfinal = LOW;\n bool AtivadorEncerra = LOW;\n  float tempoauxiliar = 0 ;\n  int ativador = 0 ;\n  int ativador2 = 0 ;\n  int ativador3 = 0 ;\n  float TempoFinal = 0 ;\n  int AtivadorClaro = 0 ;\n  int _AtivadorEscuro = 1 ;\n\n  digitalWrite("+Led_atto+" , HIGH );\n\n\n  while ( ( ( estado ) == ( 1 ) ) )\n  {\n \n    while ( ( ( auxiliarmsg ) == ( HIGH ) ) )\n    {\n      Serial.print(\"pronto para iniciar ...\");\n Serial.print(\"\"); \n     Serial.println();\n auxiliarmsg = LOW ;\n      AtivadorEncerra = HIGH;\n    }\n \n    tempoauxiliar = millis() ;\n    if (( ( ( __ardublockAnalogReadTempo("+sensor_LDR+") ) == ( 0 ) ) && ( ( teste1 ) == ( HIGH ) ) ))\n    {\n   ativador = ( ativador + 1 ) ;\n  }\n    while ( ( ( ativador ) == ( 1 ) ) )\n    {\n      ativador = ( ativador + 1 ) ;\n      ativador2 = 1 ;\n      teste1 = LOW ;\n      delay( 500 );\n    }\n\n    if (( ( ( ativador2 ) == ( 1 ) ) && ( ( __ardublockAnalogReadTempo("+sensor_LDR+") ) >= ( 3 ) ) ))\n    {\n      ativador3 = 1 ;\n      ativador2 = ( ativador2 + 1 ) ;\n    }\n    while ( ( ( ativador3 ) == ( 1 ) ) )\n    {\n      TempoFinal = ( millis() - tempoauxiliar ) ;\n      _AtivadorEscuro = 1 ;\n      while ( ( ( ( __ardublockAnalogReadTempo("+sensor_LDR+") ) == ( 0 ) ) && ( ( _AtivadorEscuro ) == ( 1 ) ) ) )\n      {\n        AtivadorClaro = 1 ;\n        _AtivadorEscuro = ( _AtivadorEscuro + 1 ) ;\n      }\n \n      while ( ( ( ( __ardublockAnalogReadTempo("+sensor_LDR+") ) >= ( 3 ) ) && ( ( AtivadorClaro ) == ( 1 ) ) ) )\n      {\n        ativador2 = ( ativador2 + 1 ) ;\n        AtivadorClaro = ( AtivadorClaro + 1 ) ;\n        mensagemfinal = HIGH ;\n      }\n \n      if (( ( mensagemfinal ) == ( HIGH ) ))\n       {\n         Serial.print(\"Tempo:\");\n   Serial.print(\" \");\n        Serial.print(TempoFinal / 1000);\n         Serial.print(\" segundos\");\n        Serial.println();\n        ativador3 = ( ativador3 + 1 ) ;\n  Serial.println();\n          Serial.println(\"Concluido\");\n          Serial.println(\"Aperte no Botão para finalizar essa contagem\");\n           \n       mensagemfinal = LOW ;\n \n  }\n    }\n \n   }\n \n  if ( (AtivadorEncerra == HIGH) && ( estado == 0)) {\n    Serial.println(\"Finalizado.\");\n   } \n";
            
            return codePrefix + atto_tempo_2 + codeSuffix;
        }
        

		return codePrefix + "" + codeSuffix;
	}
}

