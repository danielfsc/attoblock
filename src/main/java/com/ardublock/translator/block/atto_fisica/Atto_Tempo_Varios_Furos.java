package com.ardublock.translator.block.atto_fisica;


import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Atto_Tempo_Varios_Furos extends TranslatorBlock
{
	public Atto_Tempo_Varios_Furos(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
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

				
		TranslatorBlock ldr_block = this.getRequiredTranslatorBlockAtSocket(2);
		String sensor_LDR = ldr_block.toCode();
		
        
        TranslatorBlock contagem_furos = this.getRequiredTranslatorBlockAtSocket(3);
        String furos_varios = contagem_furos.toCode();



		

			String variaveis_globais = "int estado_3 = 0 ; \n int contador = "+ furos_varios +";";
			String vetor_interrupcao= "void interrupcao() { \n estado_3 = ( 1 - estado_3 ); \n }\n";
            String vetor_LDR = "int ardublockAnalogRead_3(int pinNumber)\n{\n  pinMode(pinNumber, INPUT);\n return \n analogRead(pinNumber);\n } \n ";
            
			 
			
		if(zera_block.toCode().equals(botao_iniciar) == zera_block.toCode().equals("2")){
            translator.addDefinitionCommand(variaveis_globais);
			translator.addDefinitionCommand(vetor_interrupcao);
			translator.addDefinitionCommand(vetor_LDR);
			translator.addSetupCommand("pinMode( "+ Led_atto +", OUTPUT); \n Serial.begin(9600); \n pinMode(2, INPUT_PULLUP); \n  attachInterrupt(0, interrupcao, RISING); \n Serial.println(\"Aperte no Botao para iniciar a obtencao dos dados\"); \n  Serial.println();\n");
            
            String atto_tempo_2 = "  bool auxiliarmsg_3 = HIGH; \n  bool teste1_3 = HIGH; \n  bool mensagemfinal_3 = LOW; bool ativador_3Encerral_3 = LOW; \n  float tempoauxiliar_3 = 0 ; \n  int ativador_3 = 0 ;\n  int ativador2_3 = 0 ;\n  int ativador3_3 = 0 ;\n  float TempoFinal_3 = 0 ;\n  int ativadorClaro_3 = 0 ;\n  int ativadorEscuro_3 = 1 ;\n  int auxconta = 1;\n  digitalWrite("+ Led_atto +" , HIGH );\n  while ( ( ( estado_3 ) == ( 1 ) ) )\n  {\n \n     while ( ( ( auxiliarmsg_3 ) == ( HIGH ) ) )\n    {\n      Serial.print(\"pronto para iniciar ...\");\n      Serial.println();\n      Serial.println();\n      auxiliarmsg_3 = LOW ;\n      ativador_3Encerral_3 = HIGH;\n    }\n\n    tempoauxiliar_3 = millis() ;\n    if (( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) == ( 0 ) ) && ( ( teste1_3 ) == ( HIGH ) ) ))\n    {\n      ativador_3 = ( ativador_3 + 1 ) ;\n    }\n    while ( ( ( ativador_3 ) == ( 1 ) ) )\n    {\n      ativador_3 = ( ativador_3 + 1 ) ;\n      ativador2_3 = 1 ;\n      teste1_3 = LOW ; \n      delay( 500 );\n    }\n \n    if (( ( ( ativador2_3 ) == ( 1 ) ) && ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) >= ( 3 ) ) ))\n    {\n      ativador3_3 = 1 ;\n      ativador2_3 = ( ativador2_3 + 1 ) ;\n    }\n    while ( ( ( ativador3_3 ) == ( 1 ) )  && ( ( auxconta < contador) ) )\n    {\n      TempoFinal_3 = ( millis() - tempoauxiliar_3 ) ;\n      ativadorEscuro_3 = 1 ;\n      while ( ( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) == ( 0 ) ) && ( ( ativadorEscuro_3 ) == ( 1 ) ) ) )\n      {\n        ativadorClaro_3 = 1 ;\n        ativadorEscuro_3 = ( ativadorEscuro_3 + 1 ) ;\n      }\n \n      while ( ( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) >= ( 3 ) ) && ( ( ativadorClaro_3 ) == ( 1 ) ) ) )\n      {\n        ativador2_3 = ( ativador2_3 + 1 ) ;\n        ativadorClaro_3 = ( ativadorClaro_3 + 1 ) ;\n        mensagemfinal_3 = HIGH ; \n      }\n \n      if (( ( mensagemfinal_3 ) == ( HIGH ) ))\n      {\n        Serial.print(\"Tempo \"); \n        Serial.print(auxconta); \n        Serial.print(\":\"); \n        Serial.print(\" \"); \n        Serial.print(TempoFinal_3 / 1000); \n        Serial.print(\" segundos\");\n        Serial.println(); \n        auxconta = ( auxconta + 1 ) ; \n        mensagemfinal_3 = LOW ;\n        if (auxconta == contador){\n          Serial.println();\n          Serial.println(\"Concluido\");\n          Serial.println(\"Aperte no Botão para finalizar essa contagem\");\n           \n          }\n       }\n    }\n \n  }\n \n  if ( (ativador_3Encerral_3 == HIGH) && ( estado_3 == 0)) {\n     Serial.println(\"Finalizado.\");\n  }\n ";
           
            return codePrefix + atto_tempo_2 + codeSuffix;
        }
		
        if(zera_block.toCode().equals(botao_iniciar) == zera_block.toCode().equals("3")){
            translator.addDefinitionCommand(variaveis_globais);
			translator.addDefinitionCommand(vetor_interrupcao);
			translator.addDefinitionCommand(vetor_LDR);
			translator.addSetupCommand("pinMode( "+ Led_atto +", OUTPUT); \n Serial.begin(9600); \n pinMode(3, INPUT_PULLUP); \n  attachInterrupt(1, interrupcao, RISING); \n Serial.println(\"Aperte no Botao para iniciar a obtencao dos dados\"); \n  Serial.println();\n");
            
            String atto_tempo_2 = "  bool auxiliarmsg_3 = HIGH; \n  bool teste1_3 = HIGH; \n  bool mensagemfinal_3 = LOW; bool ativador_3Encerral_3 = LOW; \n  float tempoauxiliar_3 = 0 ; \n  int ativador_3 = 0 ;\n  int ativador2_3 = 0 ;\n  int ativador3_3 = 0 ;\n  float TempoFinal_3 = 0 ;\n  int ativadorClaro_3 = 0 ;\n  int ativadorEscuro_3 = 1 ;\n  int auxconta = 1;\n  digitalWrite("+ Led_atto +" , HIGH );\n  while ( ( ( estado_3 ) == ( 1 ) ) )\n  {\n \n     while ( ( ( auxiliarmsg_3 ) == ( HIGH ) ) )\n    {\n      Serial.print(\"pronto para iniciar ...\");\n      Serial.println();\n      Serial.println();\n      auxiliarmsg_3 = LOW ;\n      ativador_3Encerral_3 = HIGH;\n    }\n\n    tempoauxiliar_3 = millis() ;\n    if (( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) == ( 0 ) ) && ( ( teste1_3 ) == ( HIGH ) ) ))\n    {\n      ativador_3 = ( ativador_3 + 1 ) ;\n    }\n    while ( ( ( ativador_3 ) == ( 1 ) ) )\n    {\n      ativador_3 = ( ativador_3 + 1 ) ;\n      ativador2_3 = 1 ;\n      teste1_3 = LOW ;\n      delay( 500 );\n    }\n \n    if (( ( ( ativador2_3 ) == ( 1 ) ) && ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) >= ( 3 ) ) ))\n    {\n      ativador3_3 = 1 ;\n      ativador2_3 = ( ativador2_3 + 1 ) ;\n    }\n    while ( ( ( ativador3_3 ) == ( 1 ) )  && ( ( auxconta < contador) ) )\n    {\n      TempoFinal_3 = ( millis() - tempoauxiliar_3 ) ;\n      ativadorEscuro_3 = 1 ;\n      while ( ( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) == ( 0 ) ) && ( ( ativadorEscuro_3 ) == ( 1 ) ) ) )\n      {\n        ativadorClaro_3 = 1 ;\n        ativadorEscuro_3 = ( ativadorEscuro_3 + 1 ) ;\n      }\n \n      while ( ( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) >= ( 3 ) ) && ( ( ativadorClaro_3 ) == ( 1 ) ) ) )\n      {\n        ativador2_3 = ( ativador2_3 + 1 ) ;\n        ativadorClaro_3 = ( ativadorClaro_3 + 1 ) ;\n        mensagemfinal_3 = HIGH ;\n      }\n \n      if (( ( mensagemfinal_3 ) == ( HIGH ) ))\n      {\n        Serial.print(\"Tempo \");\n        Serial.print(auxconta);\n        Serial.print(\":\");\n        Serial.print(\" \");\n        Serial.print(TempoFinal_3 / 1000);\n        Serial.print(\" segundos\");\n        Serial.println();\n        auxconta = ( auxconta + 1 ) ;\n        mensagemfinal_3 = LOW ;\n        if (auxconta == contador){\n          Serial.println();\n          Serial.println(\"Concluido\");\n          Serial.println(\"Aperte no Botão para finalizar essa contagem\");\n           \n          }\n       }\n    }\n \n  }\n \n  if ( (ativador_3Encerral_3 == HIGH) && ( estado_3 == 0)) {\n     Serial.println(\"Finalizado.\");\n  }\n ";
            
            return codePrefix + atto_tempo_2 + codeSuffix;
        }
        

		return codePrefix + "" + codeSuffix;
	}
}

