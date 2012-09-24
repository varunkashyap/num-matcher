#!/usr/bin/groovy

def numberMap = [
	1: ["02580371"],
	2: ["03145319", "02162628", "03136618", "04254110", "01598290", "05065858", "03803189", "04656261", "03492399", "02092140"],
	3: ["1640", "8990", "22359", "23214", "07170", "68951", "368200", "315587", "01120489", "03666239"],
	4: ["5901", "5970", "4740", "5117", "9301", "8761", "9518", "2507", "20358", "03889", "26848", "08990", "588960", "888038", "033050", "001229", "372778", "722443", "474982", "507769"],
	5: ["4182", "96799", "51839", "68281", "29749", "480580", "599709", "161807", "231422", "583239", "02166729", "02146579", "01180228"],
	6: ["7878", "1853", "8750", "5980", "32380", "81019", "62699", "46248", "57997", "18349", "67510", "38810", "176120", "741139", "451310", "396729", "036382"]
];

def inputArg = args[0];
def indexOfDash;
def lowerNum, higherNum;

if((indexOfDash = inputArg.indexOf("-")) != -1){
	println "Supplied Range: $inputArg"
	try{
		lowerNumStr = inputArg[0 .. indexOfDash - 1];
		higherNumStr = inputArg[(indexOfDash + 1) .. -1];

		if(higherNumStr.length() != lowerNumStr.length()){
			higherNumStr = lowerNumStr [ 0 .. (lowerNumStr.length() - higherNumStr.length() - 1)] + higherNumStr;
		} 
		lowerNum = Integer.parseInt(lowerNumStr);
		higherNum = Integer.parseInt(higherNumStr);
	}catch(NumberFormatException e){
		println "Supplied range cannot be parsed to numbers"
		exit(0);
	}
}else{
	try{
		lowerNum = Integer.parseInt(inputArg);
	}catch(NumberFormatException e){
		println "Supplied number cannot be parsed to numbers"
		exit(0);
	}
}

if(higherNum == null || higherNum < lowerNum){
	higherNum = lowerNum;
	println "WARN: Resetting higher number to $lowerNum";
}

def range = lowerNum .. higherNum;
println "Will match ${range.size()} numbers"

println "--------------- Match commencing -----------------"
def regex = "";
range.each{ number ->
	numberMap.each { entryNo, entryNumberList ->
		entryNumberList.each {
			regex = it + '$'
			def numStr = number.toString();
			def numLen = numStr.length();
			if(numLen < 8){
				(0 .. (8 - numLen)).each{
					numStr = "0" + numStr
				}
			}
			if(numStr  =~ regex){
				println ("MATCH SUCCESSFUL: $number matches with entry no: $entryNo for input $it")
			}
		}
	}
}
