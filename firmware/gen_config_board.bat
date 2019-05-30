rem This batch files reads rusefi_config.txt and produses firmware persistent configuration headers
rem the storage section of rusefi.ini is updated as well


rem lazy is broken - TS input is not considered a change
rm build/config.gen

java -jar ../java_tools/ConfigDefinition.jar -definition integration\rusefi_config.txt -ts_destination tunerstudio -ts_output_name rusefi_microrusefi.ini -prepend config/boards/microrusefi/temp.txt -skip build/config.gen


rem This would automatically copy latest file to 'dev' TS project
set ts_path="%HOMEDRIVE%%HOMEPATH%\Documents\TunerStudioProjects\dev\projectCfg"
echo %ts_path%
cp tunerstudio/rusefi_microrusefi.ini %ts_path%\mainController.ini