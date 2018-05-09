for f in ./Input/*.txt; do 
	str1="${f%.txt}.txt"
	str2="Output"
	finalStr="${str1/Input/$str2}"
	java -mx600m \
	-cp ./stanford-ner-3.9.1.jar \
	edu.stanford.nlp.ie.crf.CRFClassifier \
	-loadClassifier \
	./classifiers/english.all.3class.distsim.crf.ser.gz \
	-textFile "$f" \
	-outputFormat inlineXML > "$finalStr"
done
