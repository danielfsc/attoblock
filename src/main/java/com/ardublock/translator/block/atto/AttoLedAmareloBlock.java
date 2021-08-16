package com.ardublock.translator.block.atto;

import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class AttoLedAmareloBlock extends TranslatorBlock
{
	public AttoLedAmareloBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
	    String ret="digitalWrite(";
	    TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
	    ret = ret + tb.toCode();
		String set= "pinMode( ";
		set = set + tb.toCode();
		set = set + ", OUTPUT);";
		translator.addSetupCommand(set);
	    ret = ret + " , ";
	    tb = this.getRequiredTranslatorBlockAtSocket(1);
	    ret = ret + tb.toCode();
	    ret = ret + " ); ";
		return codePrefix + ret + codeSuffix;
	}

}
