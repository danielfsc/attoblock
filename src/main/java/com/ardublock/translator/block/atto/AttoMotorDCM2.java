package com.ardublock.translator.block.atto;

import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class AttoMotorDCM2 extends TranslatorBlock
{
	public AttoMotorDCM2(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock pinBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String portNum = pinBlock.toCode();
		
		TranslatorBlock veloc_block = this.getRequiredTranslatorBlockAtSocket(1);
		String value = veloc_block.toCode();
		
		TranslatorBlock timeBlock = this.getRequiredTranslatorBlockAtSocket(2);
			String time_segundos_string = timeBlock.toCode();
			int time_int = Integer.parseInt(time_segundos_string);
			time_int = time_int*1000;
			String time_segundos = ""+(time_int);
				 
		
		int value_int = Integer.parseInt(value);
		// Motor gira para trás
		if(value_int < 0){
			if((pinBlock.toCode().equals(portNum)) == (pinBlock.toCode().equals("10"))){
					value_int = value_int*(-1);
					String value_string = ""+(value_int);
					translator.addSetupCommand("pinMode("+portNum+", OUTPUT);");
					String ret = "digitalWrite("+ portNum +", LOW); \n analogWrite( 5 ," + value_string +");\n delay("+time_segundos+");";
					return codePrefix + ret + codeSuffix;
			}
			if((pinBlock.toCode().equals(portNum)) == (pinBlock.toCode().equals("11"))){
					value_int = value_int*(-1);
					String value_string = ""+(value_int);
					translator.addSetupCommand("pinMode("+portNum+", OUTPUT);");
					String ret = "digitalWrite("+ portNum +", LOW); \n analogWrite( 6 ," + value_string +");\n delay("+time_segundos+");";
					return codePrefix + ret + codeSuffix;
			}
		}
		
		// Motor gira para frente
		if(value_int > 0){
				if((pinBlock.toCode().equals(portNum)) == (pinBlock.toCode().equals("10"))){
					String value_string = ""+(value_int);
					translator.addSetupCommand("pinMode("+portNum+", OUTPUT);");
					String ret = "digitalWrite("+ portNum +", HIGH); \n analogWrite( 5 ," + value_string +");\n delay("+time_segundos+");";
					return codePrefix + ret + codeSuffix;
			}
			if((pinBlock.toCode().equals(portNum)) == (pinBlock.toCode().equals("11"))){
					String value_string = ""+(value_int);
					translator.addSetupCommand("pinMode("+portNum+", OUTPUT);");
					String ret = "digitalWrite("+ portNum +", HIGH); \n analogWrite( 6 ," + value_string +");\n delay("+time_segundos+");";
					return codePrefix + ret + codeSuffix;
			}
		}
		
		// Motor parado
		if(value_int == 0){
				if((pinBlock.toCode().equals(portNum)) == (pinBlock.toCode().equals("10"))){
					String value_string = ""+(value_int);
					translator.addSetupCommand("pinMode("+portNum+", OUTPUT);");
					String ret = "digitalWrite("+ portNum +", HIGH); \n analogWrite( 5 ," + value_string +");\n delay("+time_segundos+");";
					return codePrefix + ret + codeSuffix;
			}
			if((pinBlock.toCode().equals(portNum)) == (pinBlock.toCode().equals("11"))){
					String value_string = ""+(value_int);
					translator.addSetupCommand("pinMode("+portNum+", OUTPUT);");
					String ret = "digitalWrite("+ portNum +", HIGH); \n analogWrite( 6 ," + value_string +");\n delay("+time_segundos+");";
					return codePrefix + ret + codeSuffix;
			}
		}
				
		return "";
	}

}

//i = Integer.parseInt(aux);  		//transformar de String para inteiro
//String aux = ""+(i);				// Transformar de inteiro para String

