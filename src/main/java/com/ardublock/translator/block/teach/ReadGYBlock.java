package com.ardublock.translator.block.teach;

//import com.ardublock.core.Context;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ReadGYBlock extends TranslatorBlock
{
	public ReadGYBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{


		String ret = "readValue( ";
		
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);

		ret += tb.toCode() + " , ";
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		
		ret += tb.toCode() + " ) ";

	    return codePrefix + ret + codeSuffix;
    }
}
