package com.ardublock.translator.block.atto_fisica;


import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Atto_Conserv_Energ extends TranslatorBlock
{
	public Atto_Conserv_Energ(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
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
        
        TranslatorBlock luminosidade = this.getRequiredTranslatorBlockAtSocket(3);
			String string_luminos = luminosidade.toCode();
		



		

			String variaveis_globais = "bool _ABVAR_1_funcaoA= false ; \n bool _ABVAR_2_funcaoB= false ; \n int _ABVAR_3_estado = 0 ; \n bool _ABVAR_4_Auxiliar= false ; \n int _ABVAR_5_TempoAux = 0 ; \n double _ABVAR_6_LDR = 0.0; \n int _ABVAR_7_TempoFinal = 0; \n bool _ABVAR_8_MensagemFinal= false; \n bool Ativador_Encerra = LOW;";
			String vetor_botao= "boolean __ardublockDigitalReadTempo(int pinNumber) \n {\n pinMode(pinNumber, INPUT);\n return\n digitalRead(pinNumber); \n }\n";
            String vetor_LDR = "int __ardublockAnalogReadTempo(int pinNumber)\n{\n  pinMode(pinNumber, INPUT);\n return \n analogRead(pinNumber);\n } \n ";
            
			 
			
		
		
			translator.addDefinitionCommand(variaveis_globais);
			translator.addDefinitionCommand(vetor_botao);
			translator.addDefinitionCommand(vetor_LDR);
			translator.addSetupCommand("pinMode( " +Led_atto+ ", OUTPUT); \n Serial.begin(9600);\n Serial.println(\"Aperte no Botao para iniciar a obtencao dos dados\"); \n  Serial.println();");
		
        
        String atto_tempo = "_ABVAR_1_funcaoA = __ardublockDigitalReadTempo("+botao_iniciar+");\n  if (( ( ( _ABVAR_1_funcaoA ) == ( HIGH ) ) && ( ( _ABVAR_2_funcaoB ) == ( LOW ) ) ))\n {\n _ABVAR_3_estado = ( 1 - _ABVAR_3_estado ) ;\n  delay( 500 );\n    _ABVAR_4_Auxiliar = HIGH ;\n  }\n  _ABVAR_2_funcaoB = _ABVAR_1_funcaoA ;\n  _ABVAR_5_TempoAux = millis() ;\n  digitalWrite("+Led_atto+" , HIGH ); \n  _ABVAR_6_LDR = __ardublockAnalogReadTempo("+sensor_LDR+") ;\n  if (( ( _ABVAR_3_estado ) == ( 1 ) )) \n  { \n    _ABVAR_5_TempoAux = millis() ;\n    _ABVAR_7_TempoFinal = 0 ;\n    while ( ( ( _ABVAR_4_Auxiliar ) == ( HIGH ) ) ) \n    { \n    Serial.print(\"Pronto para Iniciar...\");\n      Serial.print(\"\");\n  Serial.println();\n _ABVAR_4_Auxiliar = LOW;\n      _ABVAR_8_MensagemFinal = LOW;\n Ativador_Encerra = HIGH; \n   }\n    while ( ( ( _ABVAR_6_LDR ) < ( "+string_luminos+" ) ) )\n    {\n      _ABVAR_6_LDR = __ardublockAnalogReadTempo("+sensor_LDR+");\n      _ABVAR_7_TempoFinal = ( millis() - _ABVAR_5_TempoAux );\n      _ABVAR_8_MensagemFinal = HIGH;\n    }\n \n    delay( 20 );\n    while ( ( ( _ABVAR_8_MensagemFinal ) == ( HIGH ) ) )\n    {\n      Serial.print(\"Carregando...\");\n      Serial.print(\" \");\n      Serial.println();\n      Serial.print(\".\");\n      Serial.print(\" \");\n      Serial.println();\n      Serial.print(\".\");\n      Serial.print(\" \");\n      Serial.println();\n      delay( 1000 );\n      Serial.print(\"Tempo Final:\");\n      Serial.print(\"\");\n      Serial.print(( _ABVAR_7_TempoFinal));\n      Serial.print(\" \");\n      Serial.print(\"Milisegundos\");\n      Serial.print(\"\");\n      Serial.println();\n      Serial.print(\".\");\n      Serial.print(\" \");\n      Serial.println();\n      Serial.print(\".\");\n      Serial.print(\"\");\n       Serial.println();\n      _ABVAR_4_Auxiliar = HIGH;\n      _ABVAR_8_MensagemFinal = LOW;\n      _ABVAR_7_TempoFinal = 0;\n    }\n  } \n if((Ativador_Encerra == HIGH) && (_ABVAR_3_estado== 0)){ \n Serial.println(); \n Serial.println(\"Finalizado.  \");\n Serial.println(); \n Ativador_Encerra = LOW; \n  }\n";
		return codePrefix + atto_tempo + codeSuffix;
	}
}
