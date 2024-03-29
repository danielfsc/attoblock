package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class TeachUltrasonic2Block extends TranslatorBlock
{
	public TeachUltrasonic2Block(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	private final static String ultraSonicFunction = "int ardublockUltrasonicSensorCodeAutoGeneratedReturnCM(int trigPin, int echoPin)\n{\n  long duration;\n  pinMode(trigPin, OUTPUT);\n  pinMode(echoPin, INPUT);\n  digitalWrite(trigPin, LOW);\n  delayMicroseconds(2);\n  digitalWrite(trigPin, HIGH);\n  delayMicroseconds(20);\n  digitalWrite(trigPin, LOW);\n  duration = pulseIn(echoPin, HIGH);\n  duration = duration / 59;\n if ((duration < 2) || (duration > 300)) return false;\n return duration;\n}\n";
	
	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String trigPin;
		String echoPin;
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		trigPin = translatorBlock.toCode();
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		echoPin = translatorBlock.toCode();
		
		translator.addSetupCommand("digitalWrite( " + trigPin + " , LOW );\n");
		
		translator.addDefinitionCommand(ultraSonicFunction);
		
		String ret = "\tardublockUltrasonicSensorCodeAutoGeneratedReturnCM( " + trigPin + " , " + echoPin + " )";
		

		return codePrefix + ret + codeSuffix;
	}
	
}
