package com.ardublock.translator.block.atto_fisica;


import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Atto_Pendulo extends TranslatorBlock
{
	public Atto_Pendulo (Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
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
        
        TranslatorBlock luminosidade = this.getRequiredTranslatorBlockAtSocket(4);
			String string_luminos = luminosidade.toCode();


            int soma = Integer.parseInt(string_luminos);
            int soma_total = soma + 5;
            String sensor_luminos = Integer.toString(soma_total);
		

			String variaveis_globais = "int estado_P = 0 ; \n int contador_P = "+ furos_varios +";";
			String vetor_interrupcao_P= "void interrupcao_P() { \n estado_P = ( 1 - estado_P ); \n }\n";
            String vetor_LDR = "int ardublockAnalogRead_3(int pinNumber)\n{\n  pinMode(pinNumber, INPUT);\n return \n analogRead(pinNumber);\n } \n ";
            
			 
			
		if(zera_block.toCode().equals(botao_iniciar) == zera_block.toCode().equals("2")){
            translator.addDefinitionCommand(variaveis_globais);
			translator.addDefinitionCommand(vetor_interrupcao_P);
			translator.addDefinitionCommand(vetor_LDR);
			translator.addSetupCommand("pinMode( "+ Led_atto +", OUTPUT); \n Serial.begin(9600); \n pinMode(2, INPUT_PULLUP); \n  attachInterrupt(0, interrupcao_P, RISING); \n Serial.println(\"Aperte no Botao para iniciar a obtencao dos dados\"); \n  Serial.println();\n");
            
            String atto_tempo_2 = "  bool auxiliarmsg_P = HIGH; \n  bool teste1_P = HIGH; \n  bool mensagemfinal_P = LOW; bool ativador_Encerral_P = LOW; \n  float tempoauxiliar_P = 0 ; \n  int ativador_P = 0 ;\n  int ativador2_P = 0 ;\n  int ativador3_P = 0 ;\n  float TempoFinal_P = 0 ;\n  int ativadorClaro_P = 0 ;\n  int ativadorEscuro_P = 1 ;\n  int auxconta_P = 1;\n  digitalWrite("+ Led_atto +" , HIGH );\n  while ( ( ( estado_P ) == ( 1 ) ) )\n  {\n \n     while ( ( ( auxiliarmsg_P ) == ( HIGH ) ) )\n    {\n      Serial.print(\"pronto para iniciar ...\");\n      Serial.println();\n      Serial.println();\n      auxiliarmsg_P = LOW ;\n      ativador_Encerral_P = HIGH;\n    }\n\n    tempoauxiliar_P = millis() ;\n    if (( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) < ( "+ string_luminos +" ) ) && ( ( teste1_P ) == ( HIGH ) ) ))\n    {\n      ativador_P = ( ativador_P + 1 ) ;\n    }\n    while ( ( ( ativador_P ) == ( 1 ) ) )\n    {\n      ativador_P = ( ativador_P + 1 ) ;\n      ativador2_P = 1 ;\n      teste1_P = LOW ; \n      delay( 500 );\n    }\n \n    if (( ( ( ativador2_P ) == ( 1 ) ) && ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) >= ( "+ sensor_luminos +"  ) ) ))\n    {\n      ativador3_P = 1 ;\n      ativador2_P = ( ativador2_P + 1 ) ;\n    }\n    while ( ( ( ativador3_P ) == ( 1 ) )  && ( ( auxconta_P < 1+ contador_P) ) )\n    {\n      TempoFinal_P = ( millis() - tempoauxiliar_P ) ;\n      ativadorEscuro_P = 1 ;\n      while ( ( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) < ( "+ string_luminos +" ) ) && ( ( ativadorEscuro_P ) == ( 1 ) ) ) )\n      {\n        ativadorClaro_P = 1 ;\n        ativadorEscuro_P = ( ativadorEscuro_P + 1 ) ;\n      }\n \n      while ( ( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) >= ( "+ sensor_luminos+"  ) ) && ( ( ativadorClaro_P ) == ( 1 ) ) ) )\n      {\n        ativador2_P = ( ativador2_P + 1 ) ;\n        ativadorClaro_P = ( ativadorClaro_P + 1 ) ;\n        mensagemfinal_P = HIGH ; \n      }\n \n      if (( ( mensagemfinal_P ) == ( HIGH ) ))\n      {\n        Serial.print(\"Tempo \"); \n        Serial.print(auxconta_P ); \n        Serial.print(\":\"); \n        Serial.print(\" \"); \n        Serial.print(TempoFinal_P); \n        Serial.print(\" Milisegundos\");\n        Serial.println(); \n        auxconta_P = ( auxconta_P + 1 ) ; \n        mensagemfinal_P = LOW ;\n        if (auxconta_P == contador_P+1){\n          Serial.println();\n          Serial.println(\"Concluido\");\n          Serial.println(\"Aperte no Botão para finalizar essa contagem\");\n           \n          }\n       }\n    }\n \n  }\n \n  if ( (ativador_Encerral_P == HIGH) && ( estado_P == 0)) {\n     Serial.println(\"Finalizado.\");\n  }\n ";
           
            return codePrefix + atto_tempo_2 + codeSuffix;
        }
		
        if(zera_block.toCode().equals(botao_iniciar) == zera_block.toCode().equals("3")){
            translator.addDefinitionCommand(variaveis_globais);
			translator.addDefinitionCommand(vetor_interrupcao_P);
			translator.addDefinitionCommand(vetor_LDR);
			translator.addSetupCommand("pinMode( "+ Led_atto +", OUTPUT); \n Serial.begin(9600); \n pinMode(3, INPUT_PULLUP); \n  attachInterrupt(1, interrupcao_P, RISING); \n Serial.println(\"Aperte no Botão para iniciar a obtenção dos dados\"); \n  Serial.println();\n");
            
            String atto_tempo_2 = "  bool auxiliarmsg_P = HIGH; \n  bool teste1_P = HIGH; \n  bool mensagemfinal_P = LOW; bool ativador_Encerral_P = LOW; \n  float tempoauxiliar_P = 0 ; \n  int ativador_P = 0 ;\n  int ativador2_P = 0 ;\n  int ativador3_P = 0 ;\n  float TempoFinal_P = 0 ;\n  int ativadorClaro_P = 0 ;\n  int ativadorEscuro_P = 1 ;\n  int auxconta_P = 1;\n  digitalWrite("+ Led_atto +" , HIGH );\n  while ( ( ( estado_P ) == ( 1 ) ) )\n  {\n \n     while ( ( ( auxiliarmsg_P ) == ( HIGH ) ) )\n    {\n      Serial.print(\"pronto para iniciar ...\");\n      Serial.println();\n      Serial.println();\n      auxiliarmsg_P = LOW ;\n      ativador_Encerral_P = HIGH;\n    }\n\n    tempoauxiliar_P = millis() ;\n    if (( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) < ( "+ string_luminos +" ) ) && ( ( teste1_P ) == ( HIGH ) ) ))\n    {\n      ativador_P = ( ativador_P + 1 ) ;\n    }\n    while ( ( ( ativador_P ) == ( 1 ) ) )\n    {\n      ativador_P = ( ativador_P + 1 ) ;\n      ativador2_P = 1 ;\n      teste1_P = LOW ; \n      delay( 500 );\n    }\n \n    if (( ( ( ativador2_P ) == ( 1 ) ) && ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) >= ( "+ string_luminos +" +5 ) ) ))\n    {\n      ativador3_P = 1 ;\n      ativador2_P = ( ativador2_P + 1 ) ;\n    }\n    while ( ( ( ativador3_P ) == ( 1 ) )  && ( ( auxconta_P < 1+ contador_P) ) )\n    {\n      TempoFinal_P = ( millis() - tempoauxiliar_P ) ;\n      ativadorEscuro_P = 1 ;\n      while ( ( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) < ( "+ string_luminos +" ) ) && ( ( ativadorEscuro_P ) == ( 1 ) ) ) )\n      {\n        ativadorClaro_P = 1 ;\n        ativadorEscuro_P = ( ativadorEscuro_P + 1 ) ;\n      }\n \n      while ( ( ( ( ardublockAnalogRead_3("+ sensor_LDR +") ) >= ( "+ string_luminos +" +5 ) ) && ( ( ativadorClaro_P ) == ( 1 ) ) ) )\n      {\n        ativador2_P = ( ativador2_P + 1 ) ;\n        ativadorClaro_P = ( ativadorClaro_P + 1 ) ;\n        mensagemfinal_P = HIGH ; \n      }\n \n      if (( ( mensagemfinal_P ) == ( HIGH ) ))\n      {\n        Serial.print(\"Tempo \"); \n        Serial.print(auxconta_P ); \n        Serial.print(\":\"); \n        Serial.print(\" \"); \n        Serial.print(TempoFinal_P); \n        Serial.print(\" Milisegundos\");\n        Serial.println(); \n        auxconta_P = ( auxconta_P + 1 ) ; \n        mensagemfinal_P = LOW ;\n        if (auxconta_P == contador_P+1){\n          Serial.println();\n          Serial.println(\"Concluido\");\n          Serial.println(\"Aperte no Botão para finalizar essa contagem\");\n           \n          }\n       }\n    }\n \n  }\n \n  if ( (ativador_Encerral_P == HIGH) && ( estado_P == 0)) {\n     Serial.println(\"Finalizado.\");\n  }\n ";
            
            return codePrefix + atto_tempo_2 + codeSuffix;
        }
        

		return codePrefix + "" + codeSuffix;
	}
}

