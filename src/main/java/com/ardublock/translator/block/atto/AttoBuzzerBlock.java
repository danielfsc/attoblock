package com.ardublock.translator.block.atto;

import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class AttoBuzzerBlock extends TranslatorBlock
{

	public AttoBuzzerBlock(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException , SubroutineNotDeclaredException
	{
		TranslatorBlock pinBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock freqBlock = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock time_seconds = this.getRequiredTranslatorBlockAtSocket(2);
		String tempo = time_seconds.toCode();
			int time_int = Integer.parseInt(tempo);
			time_int = time_int*1000;
			String time_s_string = ""+(time_int);
			
		TranslatorBlock on_off = this.getRequiredTranslatorBlockAtSocket(3);
		
		
		String digitalOn = on_off.toCode();
		
		if(on_off.toCode().equals(digitalOn) == on_off.toCode().equals("HIGH")){
			String ret = "tone(" + pinBlock.toCode() + ", " + freqBlock.toCode() + ");\n delay("+time_s_string+");";
			return ret;
		}
		if(on_off.toCode().equals(digitalOn) == on_off.toCode().equals("LOW")){
			String ret = "\tnoTone(" + pinBlock.toCode() + ");\n delay("+time_s_string+");";
			return ret;
		}
		
		String ret = "tone(" + pinBlock.toCode() + ", " + freqBlock.toCode() + ");\n";
		return ret;
	}

}
